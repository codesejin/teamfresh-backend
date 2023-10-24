package com.api.teamfresh.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * 고객사 Entity
 */

@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<VOC> vocs;
    @Column
    private String name; // 고객사 이름
    @Column
    private String contactPerson; // 담당자 이름
    @Column
    private String contactNumber; // 담당자 연락처

    private Customer(String name, String contactPerson, String contactNumber) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactNumber = contactNumber;
    }

    public static Customer from(String name, String contactPerson, String contactNumber) {
        return new Customer(name, contactPerson, contactNumber);
    }
}
