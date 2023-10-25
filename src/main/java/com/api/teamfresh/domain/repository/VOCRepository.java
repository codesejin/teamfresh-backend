package com.api.teamfresh.domain.repository;

import static com.api.teamfresh.exception.util.ErrorMessages.NOT_FOUND_VOC;

import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.exception.VOCNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VOCRepository extends JpaRepository<VOC, Long> {
    default VOC getById(Long vocId){
        return findById(vocId).orElseThrow(()-> new VOCNotFoundException(NOT_FOUND_VOC));
    }
}
