package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "commodity", indexes = {
    @Index(name = "idx_commodity_name_unq", columnList = "name", unique = true),
    @Index(name = "idx_commodity_eddn_name_unq", columnList = "eddn_name", unique = true)
})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Immutable
public class Commodity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "eddn_name", nullable = false, unique = true)
    private String eddnName;

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CommodityCategory category;

    public Commodity(String name, String eddnName, CommodityCategory category) {
        this.name = name;
        this.eddnName = eddnName;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Commodity commodity = (Commodity) o;
        return id != null && Objects.equals(id, commodity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
