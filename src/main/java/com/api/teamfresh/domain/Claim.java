package com.api.teamfresh.domain;

import com.api.teamfresh.domain.constants.ClaimStatus;
import com.api.teamfresh.util.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * 클레임 Entity
 */
@Entity
public class Claim extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voc_id")
    private VOC voc; //

    @Enumerated(EnumType.STRING)
    @Column(name="claim_status")
    private ClaimStatus claimStatus;
}
