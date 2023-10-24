package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerResponse {
    private long id;

    private String customerName;

    private String contactPerson;
    private String contactNumber;
    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.customerName = customer.getName();
        this.contactPerson = customer.getContactPerson();
        this.contactNumber = customer.getContactNumber();
    }
    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(customer);
    }
}
