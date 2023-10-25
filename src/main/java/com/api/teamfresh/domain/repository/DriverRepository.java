package com.api.teamfresh.domain.repository;


import static com.api.teamfresh.exception.util.ErrorMessages.NOT_FOUND_DRIVER;

import com.api.teamfresh.domain.entity.Driver;
import com.api.teamfresh.exception.DriverNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByNameAndPhoneNumber(String name, String phoneNumber);
    default Driver getByNameAndPhoneNumber(String name, String phoneNumber){
        return findByNameAndPhoneNumber(name,phoneNumber).orElseThrow(()-> new DriverNotFoundException(NOT_FOUND_DRIVER));
    }
}
