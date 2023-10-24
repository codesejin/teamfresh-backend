package com.api.teamfresh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;

/**
 * 배상 Entity
 */
@Entity
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="voc_id")
    private VOC voc;
    @Column
    private Double amount;

}
