package com.api.teamfresh.controller.dto.request;

import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.constants.CarrierName;
import com.api.teamfresh.domain.constants.ClaimEntryType;
import com.api.teamfresh.domain.constants.VOCContent;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class CreateVOCRequest {
    private BlameType blameType; // 귀책 대상
    private VOCContent content; // 귀책 내용
    private ClaimEntryType entryType; // 클레임 인입 경로
    private String customerName; // 고객사 이름
    private String contactPerson; // 담당자 이름
    private String contactNumber; // 담당자 연락처
    private CarrierName carrierName; // 운송사 이름
    private String driverName; // 기사 이름
    private String driverPhoneNumber; // 기사 핸드폰 번호

    public void verifyRequest(CreateVOCRequest createVOCRequest) {
        if (containsNullField(createVOCRequest)) {
            throw new IllegalArgumentException("모든 필드는 null이 아니어야 합니다.");
        }
    }

    private boolean containsNullField(CreateVOCRequest createVOCRequest) {
        return Stream.of(
                createVOCRequest.blameType,
                createVOCRequest.content,
                createVOCRequest.entryType,
                createVOCRequest.customerName,
                createVOCRequest.contactPerson,
                createVOCRequest.contactNumber,
                createVOCRequest.carrierName,
                createVOCRequest.driverName,
                createVOCRequest.driverPhoneNumber
        ).anyMatch(field -> field == null);
    }
}
