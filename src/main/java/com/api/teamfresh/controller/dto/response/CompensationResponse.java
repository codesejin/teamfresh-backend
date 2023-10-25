package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.entity.Compensation;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompensationResponse {
    private long compensationId;
    private Float amount;
    private CustomerResponse customer;

    private CompensationResponse(Compensation compensation) {
        this.compensationId = compensation.getId();
        this.amount = compensation.getAmount();
        this.customer = CustomerResponse.of(compensation.getCustomer());
    }

    public static CompensationResponse of(Compensation compensation) {
        if (compensation == null) {
            return null;
        }
        return new CompensationResponse(compensation);
    }
}
