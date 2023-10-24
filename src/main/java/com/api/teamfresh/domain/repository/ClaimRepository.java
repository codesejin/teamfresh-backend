package com.api.teamfresh.domain.repository;

import static com.api.teamfresh.exception.util.ErrorMessages.NOT_FOUND_CLAIM;

import com.api.teamfresh.domain.entity.Claim;
import com.api.teamfresh.exception.ClaimNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    default Claim getById(long claimId){
        return findById(claimId).orElseThrow(()-> new ClaimNotFoundException(NOT_FOUND_CLAIM));
    }
}
