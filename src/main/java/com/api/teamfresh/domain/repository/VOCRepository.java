package com.api.teamfresh.domain.repository;

import static com.api.teamfresh.exception.util.ErrorMessages.NOT_FOUND_VOC;

import com.api.teamfresh.controller.dto.response.voc.FindVOCByDriverName;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.exception.VOCNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VOCRepository extends JpaRepository<VOC, Long> {
    default VOC getById(Long vocId){
        return findById(vocId).orElseThrow(()-> new VOCNotFoundException(NOT_FOUND_VOC));
    }

    @Query("SELECT new com.api.teamfresh.controller.dto.response.voc.FindVOCByDriverName( v.id as vocId, v.carrier.id as carrierId, v.blameType as blameType,"
            + "v.claimEntryType as claimEntryType, v.claimStatus as claimStatus, v.vocContent as vocContent,"
            + "d.name as driverName, d.phoneNumber as driverPhoneNumber, v.createdAt as created_At, v.updatedAt as updated_at )"
            + "FROM VOC v JOIN Driver d on v.carrier = d.carrier WHERE v.id = :vocId and d.name = :driverName")
    FindVOCByDriverName getVOCsByDriverName(@Param("vocId") Long vocId, @Param("driverName") String driverName);
}
