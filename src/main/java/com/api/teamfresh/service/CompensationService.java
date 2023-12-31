package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.response.CompensationResponse;
import com.api.teamfresh.domain.entity.Compensation;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.Penalty;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.domain.repository.CompensationRepository;
import com.api.teamfresh.domain.repository.DriverRepository;
import com.api.teamfresh.domain.repository.PenaltyRepository;
import com.api.teamfresh.domain.repository.VOCRepository;
import com.api.teamfresh.util.Messages;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompensationService {
    private final CompensationRepository compensationRepository;
    private final VOCRepository vocRepository;
    private final PenaltyRepository penaltyRepository;
    private final DriverRepository driverRepository;

    // 배상 전체 목록 조회
    public List<CompensationResponse> getAllCompensations() {
        List<Compensation> compensations = compensationRepository.findAll();

        return compensations.stream()
                .map(CompensationResponse::of)
                .collect(Collectors.toList());
    }

    // 배상 등록
    @Transactional
    public String createCompensation(long vocId, long penaltyId) {
        VOC voc = vocRepository.getById(vocId);
        // 고객사가 배상 요청한 경우
        if (voc.isCompensationRequested()) {
            Penalty penalty = penaltyRepository.getById(penaltyId);
            // 배상 시스템 등록
            createAndSaveCompensation(voc, penalty);
            // 기사 월급에서 차감
            deductDriverSalary(penalty);
            return Messages.COMPENSATION_REGISTER.getMessage();
        }
        // 고객사가 배상 요청 안한 경우
        return Messages.COMPENSATION_NOT_REQUESTED.getMessage();
    }

    private void createAndSaveCompensation(VOC voc, Penalty penalty) {
        Compensation compensation = Compensation.of(voc, penalty.getPenaltyAmount());
        compensationRepository.save(compensation);
    }

    private void deductDriverSalary(Penalty penalty) {
        Driver driver = getDriverByPenalty(penalty);
        driver.addMonthlyPenalty(penalty.getPenaltyAmount());
        driverRepository.save(driver);
    }

    private Driver getDriverByPenalty(Penalty penalty) {
        return driverRepository.getByNameAndPhoneNumber(
                penalty.getDriver().getName(), penalty.getDriver().getPhoneNumber()
        );
    }
}



