package com.api.teamfresh.domain;


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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;

/**
 * VOC Entity
 */
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @OneToMany(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Penalty> penalties;

    @OneToOne(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Compensation compensation;

    @Column
    private boolean isCompensationRequested; // 배상 요청 여부
}
