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
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "government")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Government {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "government_generator")
    @SequenceGenerator(name = "government_generator", sequenceName = "government_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ToString.Exclude
    @Column(name = "eddn_name", unique = true, nullable = false)
    private String eddnName;

    @ToString.Exclude
    @Column(name = "eddb_id", unique = true)
    private Long eddbId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Government that = (Government) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
