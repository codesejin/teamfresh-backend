package com.api.teamfresh.domain.repository;

import static com.api.teamfresh.exception.util.ErrorMessages.NOT_FOUND_PENALTY;

import com.api.teamfresh.domain.entity.Penalty;
import com.api.teamfresh.exception.PenaltyNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    default Penalty getById(Long penaltyId){
        return findById(penaltyId).orElseThrow(()-> new PenaltyNotFoundException(NOT_FOUND_PENALTY));
    }
}
