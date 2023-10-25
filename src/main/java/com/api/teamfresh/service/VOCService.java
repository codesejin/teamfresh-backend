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
import com.api.teamfresh.domain.repository.PenaltyRepository;
import com.api.teamfresh.domain.repository.VOCRepository;
import java.util.ArrayList;
import java.util.List;
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
    @Transactional
    public List<AllVOCResponse> getAllVOC() {
        List<VOC> vocs = vocRepository.findAll();
        List<AllVOCResponse> response = new ArrayList<>();
        for (VOC x : vocs) {
            AllVOCResponse allVOCResponse = AllVOCResponse.of(x);
            response.add(allVOCResponse);
        }
        System.out.println("vocs : " + vocs);
        System.out.println("reponse : " + response);
        return response;
    }

    // voc 등록
    @Transactional
    public CreateVOCResponse createVOC(CreateVOC createVOC) {
        Object[] carrierAndDriver = handleCarrierAndDriver(createVOC);
        Carrier carrier = (Carrier) carrierAndDriver[0];
        Driver driver = (Driver) carrierAndDriver[1];
        Customer customer = findOrSaveCustomer(createVOC);
        VOC voc = VOC.from(createVOC.getBlameType(), createVOC.getContent(), createVOC.getEntryType(), customer,
                carrier);
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
        Customer customer = customerRepository.findByNameAndContactPerson(createVOC.getCustomerName(),
                        createVOC.getContactPerson())
                .orElseGet(() -> {
                    Customer newCustomer = Customer.from(createVOC.getCustomerName(), createVOC.getContactPerson(),
                            createVOC.getContactNumber());
                    return customerRepository.save(newCustomer);
                });
        return customer;
    }

    private Driver findOrSaveDriver(CreateVOC createVOC) {
        Driver driver = driverRepository.findByNameAndPhoneNumber(createVOC.getDriverName(),
                        createVOC.getDriverPhoneNumber())
                .orElseGet(() -> {
                    // 운전기사가 존재하지 않을 경우, 신규 저장
                    Driver newDriver = Driver.from(createVOC.getDriverName(), createVOC.getDriverPhoneNumber());
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
