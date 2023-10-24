package com.api.teamfresh.domain.repository;

import com.api.teamfresh.domain.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByNameAndContactPerson(String name, String contactPerson);
}
