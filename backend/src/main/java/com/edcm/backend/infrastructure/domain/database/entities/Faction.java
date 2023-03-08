package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "faction")
@NoArgsConstructor
@AllArgsConstructor
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "allegiance_id", nullable = false)
    private Allegiance allegiance;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "government_id", nullable = false)
    private Government government;

    @Column(name = "is_player_faction", nullable = false)
    private Boolean isPlayerFaction;
}
