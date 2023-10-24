package com.api.teamfresh.domain.entity;

import com.api.teamfresh.domain.constants.CarrierName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 운송사 Entity
 */
@Data
@NoArgsConstructor
@Entity
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="carrier", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<VOC> vocs;

    @OneToMany(mappedBy = "carrier", fetch = FetchType.EAGER)
    private List<Driver> driver;
    @Enumerated(EnumType.STRING)
    @Column
    private CarrierName carrierName; // 운송사 이름

    private Carrier(CarrierName carrierName) {
        this.carrierName = carrierName;
    }

    public static Carrier from(CarrierName carrierName) {
        return new Carrier(carrierName);
    }
}
