package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.entity.Compensation;
import com.api.teamfresh.domain.entity.Customer;

public class CompensationResponse {
    private long compensationId;
    private Float amount;
    private Customer customer;

    private CompensationResponse(Compensation compensation) {
        this.compensationId = compensation.getId();
        this.amount = compensation.getAmount();
        this.customer = compensation.getCustomer();
    }

    public static CompensationResponse of (Compensation compensation) {
        if (compensation == null) {
            return null;
        }
        return new CompensationResponse(compensation);
    }
}
