package com.api.teamfresh.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 * 운송사 Entity
 */
@Entity
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="carrier", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<VOC> vocs;

    @OneToMany(mappedBy = "carrier", fetch = FetchType.EAGER)
    private List<Driver> driver;
    @Column
    private String name; // 운송사 이름
}
