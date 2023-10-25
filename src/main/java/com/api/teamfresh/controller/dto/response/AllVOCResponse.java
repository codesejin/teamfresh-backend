package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.constants.BlameType;

public class AllVOCResponse {
    private long id;
    private BlameType blameType; // 귀책 당사자
    private String content; // 귀책 내용
    private String claimEntryType;
    // 패널티 내용
    // 기사 확인 여부
    // 이의 제기 여부
    // 배상 정보
    private CarrierResponse carrier;
    private CustomerResponse customer;
}
