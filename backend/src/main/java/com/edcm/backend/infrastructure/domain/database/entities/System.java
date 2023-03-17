package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;

import java.util.Objects;

@Entity
@Table(name = "system")
@Getter
@Setter
@ToString
@Immutable
@AllArgsConstructor
@NoArgsConstructor
public class System {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Embedded
    private Coordinates coordinates;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "allegiance_id")
    private Allegiance allegiance;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "economy_id")
    private Economy economy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        System that = (System) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
