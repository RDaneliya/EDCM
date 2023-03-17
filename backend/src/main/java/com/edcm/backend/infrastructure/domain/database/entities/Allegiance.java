package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "allegiance")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Allegiance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allegiance_generator")
    @SequenceGenerator(name = "allegiance_generator", sequenceName = "allegiance_seq", allocationSize = 1)
    private Long id;
    @Column(name = "eddn_id", unique = true, nullable = false)
    private String eddnId;

    @Column(name = "eddb_id", unique = true)
    private Long eddbId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
