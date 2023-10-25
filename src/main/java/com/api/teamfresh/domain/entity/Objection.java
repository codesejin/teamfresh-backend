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
 * 이의제기 Entity
 */
@NoArgsConstructor
@Entity
public class Objection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "penalty_id")
    private Penalty penalty; // 이의제기 대상 패널티

    @Column
    private String content;

    private Objection(Penalty penalty, String content) {
        this.penalty = penalty;
        this.content = content;
    }
    public static Objection of(Penalty penalty, String content) {
        return new Objection(penalty, content);
    }
}
