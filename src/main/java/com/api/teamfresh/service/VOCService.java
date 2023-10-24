package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.request.CreateVOC;
import com.api.teamfresh.controller.dto.response.CreateVOCResponse;
import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.entity.Carrier;
import com.api.teamfresh.domain.entity.Claim;
import com.api.teamfresh.domain.entity.Customer;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.domain.repository.CarrierRepository;
import com.api.teamfresh.domain.repository.ClaimRepository;
import com.api.teamfresh.domain.repository.CustomerRepository;
import com.api.teamfresh.domain.repository.DriverRepository;
import com.api.teamfresh.domain.repository.VOCRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VOCService {

    private final ClaimRepository claimRepository;

    private final DriverRepository driverRepository;

    private final CarrierRepository carrierRepository;

    private final VOCRepository vocRepository;

    private final CustomerRepository customerRepository;

    @Transactional
    public CreateVOCResponse createVOC(CreateVOC createVOC) {

        Claim claim = claimRepository.getById(createVOC.getClaimId());

        Object[] carrierAndDriver = handleCarrierAndDriver(createVOC);
        Carrier carrier = (Carrier) carrierAndDriver[0];
        Driver driver = (Driver) carrierAndDriver[1];

        Customer customer = findOrSaveCustomer(createVOC);
        VOC voc = VOC.from(createVOC.getBlameType(), customer, carrier);
        VOC savedVoc = vocRepository.save(voc);
        return CreateVOCResponse.of(savedVoc, carrier, driver, customer);
    }

    private Object[] handleCarrierAndDriver(CreateVOC createVOC) {
        Object[] objects = new Object[2];
        Carrier carrier = findOrSaveCarrier(createVOC);
        Driver driver = findOrSaveDriver(createVOC);
        driver.setCarrier(carrier);
        driverRepository.save(driver);
        objects[0] = carrier;
        objects[1] = driver;
        return objects;
    }

    private Customer findOrSaveCustomer(CreateVOC createVOC) {
        Customer customer = customerRepository.findByNameAndContactPerson(createVOC.getCustomerName(), createVOC.getContactPerson())
                .orElseGet(() -> {
                    Customer newCustomer = Customer.from(createVOC.getCustomerName(), createVOC.getContactPerson(),
                            createVOC.getContactNumber());
                    return customerRepository.save(newCustomer);
                });
        return customer;
    }

    private Driver findOrSaveDriver(CreateVOC createVOC) {
        Driver driver = driverRepository.findByNameAndPhoneNumber(createVOC.getDriverName(),
                        createVOC.getDriverPhoneNo())
                .orElseGet(() -> {
                    // 운전기사가 존재하지 않을 경우, 신규 저장
                    Driver newDriver = Driver.from(createVOC.getDriverName(), createVOC.getDriverPhoneNo());
                    return driverRepository.save(newDriver);
                });
        return driver;
    }

    private Carrier findOrSaveCarrier(CreateVOC createVOC) {
        Carrier carrier = carrierRepository.findByCarrierName(createVOC.getCarrierName())
                .orElseGet(() -> {
                    // 운송사가 존재하지 않을 경우, 신규 저장
                    Carrier newCarrier = Carrier.from(createVOC.getCarrierName());
                    return carrierRepository.save(newCarrier);
                });
        return carrier;
    }
}
