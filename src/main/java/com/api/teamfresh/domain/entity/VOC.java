package com.api.teamfresh.domain.entity;


import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.constants.ClaimEntryType;
import com.api.teamfresh.domain.constants.ClaimStatus;
import com.api.teamfresh.domain.constants.VOCContent;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_status")
    private ClaimStatus claimStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "voc_content")
    private VOCContent vocContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_entry_type")
    private ClaimEntryType claimEntryType;

    @Enumerated(EnumType.STRING)
    @Column
    private BlameType blameType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @OneToOne(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Compensation compensation;

    @OneToOne(mappedBy = "voc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Penalty penalty;

    @Column
    private boolean isCompensationRequested;

    private VOC(BlameType blameType, VOCContent content, ClaimEntryType claimEntryType, Customer customer,
                Carrier carrier) {
        this.claimStatus = ClaimStatus.INCOMING;
        this.vocContent = content;
        this.claimEntryType = claimEntryType;
        this.blameType = blameType;
        this.customer = customer;
        this.carrier = carrier;
        this.isCompensationRequested = false;
    }

    public static VOC from(BlameType blameType, VOCContent content, ClaimEntryType claimEntryType, Customer customer, Carrier carrier) {
        return new VOC(blameType,content, claimEntryType,customer, carrier);
    }

    public void updateCompensationRequested() {
        this.isCompensationRequested = true;
    }
}
