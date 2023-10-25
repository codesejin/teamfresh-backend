package com.api.teamfresh.domain.repository;

import com.api.teamfresh.domain.entity.Objection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectionRepository extends JpaRepository<Objection, Long> {
}
