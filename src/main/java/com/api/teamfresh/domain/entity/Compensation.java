package com.api.teamfresh.domain.entity;

import com.api.teamfresh.util.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

/**
 * 배상 Entity
 */
@NoArgsConstructor
@Entity
public class Compensation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="voc_id")
    private VOC voc;
    @Column
    private Float amount;

    private Compensation(VOC voc, Float amount) {
        this.voc = voc;
        this.amount = amount;
    }
    public static Compensation of(VOC voc, Float amount) {
        return new Compensation(voc,amount);
    }
}
