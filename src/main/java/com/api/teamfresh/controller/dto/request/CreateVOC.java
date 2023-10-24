package com.api.teamfresh.controller.dto.request;

import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.constants.CarrierName;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CreateVOC {
    Long claimId;
    BlameType blameType;
    private String customerName; // 고객사 이름
    private String contactPerson; // 담당자 이름
    private String contactNumber; // 담당자 연락처
    private CarrierName carrierName; // 운송사 이름
    private String DriverName; // 기사 이름
    private String DriverPhoneNo; // 기사 핸드폰 번호
}
