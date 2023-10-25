package com.api.teamfresh.domain.entity;

import com.api.teamfresh.domain.constants.ObjectionStatus;
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
 * 패널티 Entity
 */
@Getter
@NoArgsConstructor
@Entity
public class Penalty extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="voc_id")
    private VOC voc;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="driver_id")
    private Driver driver;

    @Column(name = "penalty_amount")
    private Float penaltyAmount; // 패널티 금액

    @Column
    private boolean confirmedByDriver;

    @OneToOne(mappedBy = "penalty", cascade = CascadeType.ALL, orphanRemoval = true)
    private Objection objection;

    @Enumerated(EnumType.STRING)
    @Column(name = "objection_status", columnDefinition = "VARCHAR(30) DEFAULT '이의 제기 없음'")
    private ObjectionStatus objectionStatus; // 이의 제기 여부

    private Penalty(VOC voc, Driver driver, Float penaltyAmount) {
        this.voc = voc;
        this.driver = driver;
        this.penaltyAmount = penaltyAmount;
        this.confirmedByDriver = false;
        this.objectionStatus = ObjectionStatus.NO_OBJECTION;
    }

    public static Penalty of(VOC voc, Driver driver, Float penaltyAmount) {
        return new Penalty(voc, driver, penaltyAmount);
    }

    public void confirmByDriver() {
        this.confirmedByDriver=true;
    }

    public void rejectConfirmationByDriver(Objection objection) {
        this.confirmedByDriver=false;
        this.objectionStatus = ObjectionStatus.OBJECTION_RAISED;
        this.objection = objection;
    }
}
