package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Allegiance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AllegianceRepository extends JpaRepository<Allegiance, Long> {
    boolean existsByEddnIdOrEddbIdOrNameAllIgnoreCase(
            String eddnId, Long eddbId, String name
    );

    Allegiance findByEddnId(String eddnId);

    boolean existsByName(String name);
    Optional<Allegiance> findByName(String name);

    Set<Allegiance> findAllByNameIn(List<String> name);


}
