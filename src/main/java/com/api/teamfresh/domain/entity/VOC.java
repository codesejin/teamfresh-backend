package com.api.teamfresh.domain.entity;


import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.util.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * VOC Entity
 */
@Getter
@NoArgsConstructor
@Entity
public class VOC extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Claim claim;

    @Enumerated(EnumType.STRING)
    @Column
    private BlameType blameType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @OneToOne(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Compensation compensation;

    private VOC(BlameType blameType, Customer customer, Carrier carrier) {
        this.blameType = blameType;
        this.customer = customer;
        this.carrier = carrier;
    }
    public static VOC from(BlameType blameType, Customer customer, Carrier carrier) {
        return new VOC(blameType ,customer, carrier);
    }
}
