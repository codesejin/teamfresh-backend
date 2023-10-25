package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.constants.CarrierName;
import com.api.teamfresh.domain.entity.Carrier;
import com.api.teamfresh.domain.entity.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarrierResponse {
    private long id;
    private CarrierName carrierName;
    private String DriverName;
    private String DriverPhoneNumber;

    public CarrierResponse(Carrier carrier, Driver driver) {
        this.id = carrier.getId();
        this.carrierName = carrier.getCarrierName();
        this.DriverName = driver.getName();
        this.DriverPhoneNumber = driver.getPhoneNumber();
    }

    public CarrierResponse(Carrier carrier) {
        this.id = carrier.getId();
        this.carrierName = carrier.getCarrierName();
    }

    public static CarrierResponse of(Carrier carrier, Driver driver) {
        return new CarrierResponse(carrier, driver);
    }

    public static CarrierResponse createOnlyCarrierInfo(Carrier carrier) {
        return new CarrierResponse(carrier);
    }
}
