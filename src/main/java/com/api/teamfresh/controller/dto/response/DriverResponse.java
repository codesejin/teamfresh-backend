package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.entity.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DriverResponse {
    private long driverId;;
    private String driverName;

    private String driverPhoneNumber;

    private DriverResponse(Driver driver) {
        this.driverId = driver.getId();
        this.driverName = driver.getName();
        this.driverPhoneNumber = driver.getPhoneNumber();
    }

    public static DriverResponse of(Driver driver) {
        return new DriverResponse(driver);
    }
}
