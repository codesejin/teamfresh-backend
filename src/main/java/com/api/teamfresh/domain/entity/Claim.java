package com.api.teamfresh.domain.entity;

import com.api.teamfresh.domain.constants.ClaimStatus;
import com.api.teamfresh.domain.constants.ClaimType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 클레임 Entity
 */
@Getter
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(name="claim_type")
    private ClaimType claimType;

    public Claim(ClaimType claimType) {
        this.claimStatus = ClaimStatus.INCOMING;
        this.claimType = claimType;
    }
}
