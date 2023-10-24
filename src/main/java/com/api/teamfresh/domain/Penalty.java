package com.api.teamfresh.domain;

import com.api.teamfresh.util.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * 패널티 Entity
 */
@Entity
public class Penalty extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="voc_id")
    private VOC voc;
    @Column
    private boolean confirmedByDriver;

    public void confirmByDriver() {
        this.confirmedByDriver=true;
    }

    public void rejectConfirmationByDriver() {
        this.confirmedByDriver=false;
    }
}
