package com.api.teamfresh.domain.repository;

import com.api.teamfresh.domain.constants.CarrierName;
import com.api.teamfresh.domain.entity.Carrier;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository <Carrier, Long> {

    Optional<Carrier> findByCarrierName(CarrierName carrierName);
}
