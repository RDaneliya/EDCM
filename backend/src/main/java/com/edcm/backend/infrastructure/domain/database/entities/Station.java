package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "station")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @Column(name = "id")
    @Immutable
    private Long id;

    @Column(name = "name", nullable = false)
    @Immutable
    private String name;

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "system", nullable = false)
    private System system;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "station", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private List<StationCommodity> commodities = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "station_economies",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "economies_id"))
    private Set<Economy> economies = new LinkedHashSet<>();

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "station_type_id", referencedColumnName = "id")
    private StationType stationType;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "station_station_services",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "station_services_id"))
    private Set<StationService> stationServices = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faction_state_id")
    private FactionState factionState;
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();


    public Station(Long id, String name, System system) {
        this.id = id;
        this.name = name;
        this.system = system;
    }


    public void addCommodities(List<StationCommodity> commodities) {
        this.commodities.addAll(commodities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Station that = (Station) o;
        return id != null && Objects.equals(id, that.id);
    }

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean isCarrier() {
        return name.matches("([A-Z0-9]){3}-([A-Z0-9]){3}");
    }
}
