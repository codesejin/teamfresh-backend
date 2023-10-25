package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.request.ConfirmPenaltyRequest;
import com.api.teamfresh.controller.dto.request.CreatePenaltyRequest;
import com.api.teamfresh.controller.dto.response.CreatePenaltyResponse;
import com.api.teamfresh.controller.dto.response.voc.FindVOCByDriverName;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.Objection;
import com.api.teamfresh.domain.entity.Penalty;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.domain.repository.DriverRepository;
import com.api.teamfresh.domain.repository.ObjectionRepository;
import com.api.teamfresh.domain.repository.PenaltyRepository;
import com.api.teamfresh.domain.repository.VOCRepository;
import com.api.teamfresh.util.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PenaltyService {
    private final VOCRepository vocRepository;

    private final DriverRepository driverRepository;
    private final PenaltyRepository penaltyRepository;
    private final ObjectionRepository objectionRepository;

    @Transactional
    public CreatePenaltyResponse createPenalty(CreatePenaltyRequest penaltyRequest) {
        VOC voc = vocRepository.getById(penaltyRequest.getVocId());
        FindVOCByDriverName vocByDriverName = vocRepository.getVOCsByDriverName(voc.getId(),
                penaltyRequest.getDriverName());
        Driver driver = driverRepository.getByNameAndPhoneNumber(vocByDriverName.getDriverName(),
                vocByDriverName.getDriverPhoneNumber());
        Penalty savedPenalty =
                penaltyRepository.save(Penalty.of(voc, driver, penaltyRequest.getPenaltyAmount()));
        return CreatePenaltyResponse.of(savedPenalty);
    }

    @Transactional
    public String confirmPenalty(Long penaltyId, ConfirmPenaltyRequest confirmPenaltyRequest) {
        Penalty penalty = penaltyRepository.getById(penaltyId);
        // 기사가 승인할 경우
        if (confirmPenaltyRequest.isConfirmed()) {
            return acceptedPenalty(penalty);
        }
        // 이의 제기할 경우
        return rejectedPenalty(confirmPenaltyRequest, penalty);

    }

    private static String acceptedPenalty(Penalty penalty) {
        penalty.confirmByDriver();
        return Messages.CONFIRMED.getMessage();
    }

    private String rejectedPenalty(ConfirmPenaltyRequest confirmPenaltyRequest, Penalty penalty) {
        confirmPenaltyRequest.verifyConfirmed();
        Objection saveObjection =
                objectionRepository.save(Objection.of(penalty, confirmPenaltyRequest.getContent()));
        penalty.rejectConfirmationByDriver(saveObjection);
        penaltyRepository.save(penalty);
        return Messages.OBJECTION.getMessage();
    }
}
