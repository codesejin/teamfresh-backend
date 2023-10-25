package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.request.CreateVOC;
import com.api.teamfresh.controller.dto.response.voc.AllVOCResponse;
import com.api.teamfresh.controller.dto.response.voc.CreateVOCResponse;
import com.api.teamfresh.domain.entity.Carrier;
import com.api.teamfresh.domain.entity.Customer;
import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.domain.repository.CarrierRepository;
import com.api.teamfresh.domain.repository.CustomerRepository;
import com.api.teamfresh.domain.repository.DriverRepository;
import com.api.teamfresh.domain.repository.VOCRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VOCService {

    private final DriverRepository driverRepository;

    private final CarrierRepository carrierRepository;

    private final VOCRepository vocRepository;

    private final CustomerRepository customerRepository;

    // voc 전체 목록 조회
    public List<AllVOCResponse> getAllVOC() {
        List<VOC> vocs = vocRepository.findAll();

        return vocs.stream()
                .map(AllVOCResponse::of)
                .collect(Collectors.toList());
    }

    // voc 등록
    @Transactional
    public CreateVOCResponse createVOC(CreateVOC createVOC) {
        Object[] carrierAndDriver = handleCarrierAndDriver(createVOC);
        Carrier carrier = (Carrier) carrierAndDriver[0];
        Driver driver = (Driver) carrierAndDriver[1];
        Customer customer = findOrSaveCustomer(createVOC);

        VOC voc = VOC.from(createVOC.getBlameType(), createVOC.getContent(), createVOC.getEntryType(), customer, carrier);
        VOC savedVoc = vocRepository.save(voc);

        return CreateVOCResponse.of(savedVoc, carrier, driver, customer);
    }

    private Object[] handleCarrierAndDriver(CreateVOC createVOC) {
        Carrier carrier = findOrSaveCarrier(createVOC);
        Driver driver = findOrSaveDriver(createVOC);
        driver.setCarrier(carrier);
        driverRepository.save(driver);

        return new Object[]{carrier, driver};
    }

    private Customer findOrSaveCustomer(CreateVOC createVOC) {
        return customerRepository
                .findByNameAndContactPerson(createVOC.getCustomerName(), createVOC.getContactPerson())
                .orElseGet(() -> {
                    Customer newCustomer = Customer.from(createVOC.getCustomerName(), createVOC.getContactPerson(), createVOC.getContactNumber());
                    return customerRepository.save(newCustomer);
                });
    }

    private Driver findOrSaveDriver(CreateVOC createVOC) {
        return driverRepository
                .findByNameAndPhoneNumber(createVOC.getDriverName(), createVOC.getDriverPhoneNumber())
                .orElseGet(() -> {
                    Driver newDriver = Driver.from(createVOC.getDriverName(), createVOC.getDriverPhoneNumber());
                    driverRepository.save(newDriver);
                    return newDriver;
                });
    }

    private Carrier findOrSaveCarrier(CreateVOC createVOC) {
        return carrierRepository
                .findByCarrierName(createVOC.getCarrierName())
                .orElseGet(() -> {
                    Carrier newCarrier = Carrier.from(createVOC.getCarrierName());
                    carrierRepository.save(newCarrier);
                    return newCarrier;
                });
    }
}
