package com.api.teamfresh.domain.repository;

import com.api.teamfresh.domain.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
}
