package com.api.teamfresh.controller.dto.response;

import com.api.teamfresh.domain.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerResponse {
    private long customerId;
    private String customerName;
    private String contactPerson;
    private String contactNumber;

    public CustomerResponse(Customer customer) {
        this.customerId = customer.getId();
        this.customerName = customer.getName();
        this.contactPerson = customer.getContactPerson();
        this.contactNumber = customer.getContactNumber();
    }

    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(customer);
    }
}
