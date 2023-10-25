package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.request.CreatePenaltyRequest;
import com.api.teamfresh.controller.dto.response.CreatePenaltyResponse;
import com.api.teamfresh.controller.dto.response.voc.FindVOCByDriverName;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.Penalty;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.domain.repository.DriverRepository;
import com.api.teamfresh.domain.repository.PenaltyRepository;
import com.api.teamfresh.domain.repository.VOCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PenaltyService {
    private final VOCRepository vocRepository;

    private final DriverRepository driverRepository;
    private final PenaltyRepository penaltyRepository;
    public CreatePenaltyResponse createPenalty(CreatePenaltyRequest penaltyRequest) {
        VOC voc = vocRepository.getById(penaltyRequest.getVocId());
        FindVOCByDriverName vocByDriverName = vocRepository.getVOCsByDriverName(voc.getId(),
                penaltyRequest.getDriverName());
        Driver driver = driverRepository.getByNameAndPhoneNumber(vocByDriverName.getDriverName(),
                vocByDriverName.getDriverPhoneNumber());
        Penalty savedPenalty =
                penaltyRepository.save(Penalty.of(driver, penaltyRequest.getPenaltyAmount()));
        return CreatePenaltyResponse.of(savedPenalty);
    }
}
