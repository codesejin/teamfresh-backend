package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.constants.ObjectionStatus;
import com.api.teamfresh.domain.entity.Penalty;
import lombok.Getter;

@Getter
public class CreatePenaltyResponse {
    private long penaltyId;
    private String driverName;

    private String driverPhoneNumber;

    private Float penaltyAmount;
    private boolean confirmedByDriver;
    private String objectionStatus;
    private CreatePenaltyResponse(Penalty penalty) {
        this.penaltyId = penalty.getId();
        this.driverName = penalty.getDriver().getName();
        this.driverPhoneNumber = penalty.getDriver().getPhoneNumber();
        this.penaltyAmount = penalty.getPenaltyAmount();
        this.confirmedByDriver = penalty.isConfirmedByDriver();
        this.objectionStatus = penalty.getObjectionStatus().getDescription();
    }
    public static CreatePenaltyResponse of(Penalty penalty) {
        return new CreatePenaltyResponse(penalty);
    }
}
