package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.constants.ObjectionStatus;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.Objection;
import com.api.teamfresh.domain.entity.Penalty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PenaltyResponse {
    private long penaltyId;
    private Driver driver;
    private Float penaltyAmount;
    private boolean confirmedByDriver;
    // 이의 제기 여부 (이의 내용이 무엇인지는 요구사항에 없으므로 여부만 파악)
    private ObjectionStatus objectionStatus;

    private PenaltyResponse(Penalty penalty) {
        this.penaltyId = penalty.getId();
        this.driver = penalty.getDriver();
        this.penaltyAmount = penalty.getPenaltyAmount();
        this.confirmedByDriver = penalty.isConfirmedByDriver();
        this.objectionStatus = penalty.getObjectionStatus(); //
    }
    public static PenaltyResponse of(Penalty penalty) {
        if (penalty == null) {
            return null;
        }
        return new PenaltyResponse(penalty);
    }
}
