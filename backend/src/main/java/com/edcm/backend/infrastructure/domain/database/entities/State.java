package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "eddn_id", nullable = false, unique = true)
    private String eddnId;

    @Column(name = "eddb_id", unique = true)
    private Long eddbId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
