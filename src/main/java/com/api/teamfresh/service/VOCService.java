package com.api.teamfresh.service;

import com.api.teamfresh.controller.dto.request.CreateVOCRequest;
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
import com.api.teamfresh.util.APIResponse;
import com.api.teamfresh.util.Messages;
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
    public CreateVOCResponse createVOC(CreateVOCRequest createVOCRequest) {
        Object[] carrierAndDriver = handleCarrierAndDriver(createVOCRequest);
        Carrier carrier = (Carrier) carrierAndDriver[0];
        Driver driver = (Driver) carrierAndDriver[1];
        Customer customer = findOrSaveCustomer(createVOCRequest);

        VOC voc = VOC.from(createVOCRequest.getBlameType(), createVOCRequest.getContent(), createVOCRequest.getEntryType(), customer, carrier);
        VOC savedVoc = vocRepository.save(voc);

        return CreateVOCResponse.of(savedVoc, carrier, driver, customer);
    }

    // 고객사가 배상 요청 한 경우
    @Transactional
    public String updatedCompensationRequest(long vocId) {
        VOC voc = vocRepository.getById(vocId);
        voc.updateCompensationRequested();
        vocRepository.save(voc);
        return Messages.COMPENSATION_REQUESTED.getMessage();
    }

    private Object[] handleCarrierAndDriver(CreateVOCRequest createVOCRequest) {
        Carrier carrier = findOrSaveCarrier(createVOCRequest);
        Driver driver = findOrSaveDriver(createVOCRequest);
        driver.setCarrier(carrier);
        driverRepository.save(driver);

        return new Object[]{carrier, driver};
    }

    private Customer findOrSaveCustomer(CreateVOCRequest createVOCRequest) {
        return customerRepository
                .findByNameAndContactPerson(createVOCRequest.getCustomerName(), createVOCRequest.getContactPerson())
                .orElseGet(() -> {
                    Customer newCustomer = Customer.from(createVOCRequest.getCustomerName(), createVOCRequest.getContactPerson(), createVOCRequest.getContactNumber());
                    return customerRepository.save(newCustomer);
                });
    }

    private Driver findOrSaveDriver(CreateVOCRequest createVOCRequest) {
        return driverRepository
                .findByNameAndPhoneNumber(createVOCRequest.getDriverName(), createVOCRequest.getDriverPhoneNumber())
                .orElseGet(() -> {
                    Driver newDriver = Driver.from(createVOCRequest.getDriverName(), createVOCRequest.getDriverPhoneNumber());
                    driverRepository.save(newDriver);
                    return newDriver;
                });
    }

    private Carrier findOrSaveCarrier(CreateVOCRequest createVOCRequest) {
        return carrierRepository
                .findByCarrierName(createVOCRequest.getCarrierName())
                .orElseGet(() -> {
                    Carrier newCarrier = Carrier.from(createVOCRequest.getCarrierName());
                    carrierRepository.save(newCarrier);
                    return newCarrier;
                });
    }
}
