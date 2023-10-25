package com.api.teamfresh.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.security.PublicKey;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 기사 Entity
 */
@Getter
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column(name = "monthly_penalty")
    private Float monthlyPenalty; // 패널티 차감 금액

    public Driver (String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.monthlyPenalty = 0f;
    }
    public static Driver from (String name, String phoneNumber) {
        return new Driver(name, phoneNumber);
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public void addMonthlyPenalty(Float monthlyPenalty) {
        this.monthlyPenalty += monthlyPenalty;
    }
}
