package com.edcm.backend.infrastructure.domain.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.internal.util.stereotypes.Immutable;

import java.util.Objects;

@Entity
@Table(name = "commodities_at_station")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationCommodity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "station_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    @Immutable
    private Station station;

    @JoinColumn(name = "commodity_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    @Immutable
    private Commodity commodity;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "demand")
    private Long demand;

    @Column(name = "buy_price")
    private Long buyPrice;

    @Column(name = "sell_price")
    private Long sellPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationCommodity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
