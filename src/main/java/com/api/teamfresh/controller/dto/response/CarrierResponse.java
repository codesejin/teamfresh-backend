package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.constants.CarrierName;
import com.api.teamfresh.domain.entity.Carrier;
import com.api.teamfresh.domain.entity.Driver;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarrierResponse {
    private long id;
    private String carrierName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DriverResponse driver;

    public CarrierResponse(Carrier carrier, Driver driver) {
        this.id = carrier.getId();
        this.carrierName = carrier.getCarrierName().getDisplayName();
        this.driver = DriverResponse.of(driver);
    }

    public CarrierResponse(Carrier carrier) {
        this.id = carrier.getId();
        this.carrierName = carrier.getCarrierName().getDisplayName();
    }

    public static CarrierResponse of(Carrier carrier, Driver driver) {
        return new CarrierResponse(carrier, driver);
    }

    public static CarrierResponse createOnlyCarrierInfo(Carrier carrier) {
        return new CarrierResponse(carrier);
    }
}
