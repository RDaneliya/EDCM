package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface EconomyRepository extends JpaRepository<Economy, Long> {
    Optional<Economy> findByName(String name);

    Set<Economy> findAllByNameIn(Collection<String> strings);
}
