package com.api.teamfresh.controller.dto.request;

import lombok.Getter;

@Getter
public class CreatePenaltyRequest {
    private long vocId;
    private String driverName;
    private String driverPhoneNumber;
    private Float penaltyAmount;
}
