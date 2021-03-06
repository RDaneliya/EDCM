package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.internal.util.stereotypes.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "station", indexes = {
    @Index(name = "idx_stationentity_name", columnList = "name")
})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Immutable
    private Long id;

    @Column(name = "name", nullable = false)
    @Immutable
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "system", nullable = false)
    private System system;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private List<ProhibitedCommodity> prohibited = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private List<StationCommodity> commodities = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private List<StationEconomy> economies = new ArrayList<>();


    public void addCommodity(StationCommodity commodity) {
        commodity.setStation(this);
        commodities.add(commodity);
    }

    public void addProhibited(ProhibitedCommodity prohibited) {
        prohibited.setStation(this);
        this.prohibited.add(prohibited);
    }

    public void addEconomy(StationEconomy economy) {
        economy.setStation(this);
        economies.add(economy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Station that = (Station) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean isCarrier() {
        return name.matches("([A-Z0-9]){3}-([A-Z0-9]){3}");
    }
}
