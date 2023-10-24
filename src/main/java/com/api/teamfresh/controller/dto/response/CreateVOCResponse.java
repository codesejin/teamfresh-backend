package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.entity.Carrier;
import com.api.teamfresh.domain.entity.Customer;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.VOC;
import lombok.Getter;

@Getter
public class CreateVOCResponse {
    private long id;
    private BlameType blameType;
    private CarrierResponse carrier;
    private CustomerResponse customer;

    public CreateVOCResponse(VOC voc, Carrier carrier, Driver driver, Customer customer) {
        this.id = voc.getId();
        this.blameType = voc.getBlameType();
        this.carrier = CarrierResponse.of(carrier, driver);
        this.customer = CustomerResponse.of(customer);
    }

    public static CreateVOCResponse of(VOC voc, Carrier carrier, Driver driver, Customer customer) {
        return new CreateVOCResponse(voc, carrier,driver,customer);
    }
}
