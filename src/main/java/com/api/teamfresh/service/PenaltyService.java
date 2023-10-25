package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.request.CreatePenaltyRequest;
import com.api.teamfresh.controller.dto.response.CreatePenaltyResponse;
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
    public CreatePenaltyResponse createPenalty(CreatePenaltyRequest createPenaltyRequest) {

        VOC voc = vocRepository.getById(createPenaltyRequest.getVocId());
        // 운전기사 먼저 찾기
        Driver foundDriver = driverRepository.getByNameAndPhoneNumber(createPenaltyRequest.getDriverName(),
                createPenaltyRequest.getDriverPhoneNumber());
        Penalty savedPenalty =
                penaltyRepository.save(Penalty.of(foundDriver, createPenaltyRequest.getPenaltyAmount()));
        return CreatePenaltyResponse.of(savedPenalty);
    }
}
