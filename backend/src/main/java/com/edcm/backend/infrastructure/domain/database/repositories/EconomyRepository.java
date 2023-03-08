package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EconomyRepository extends JpaRepository<Economy, Long> {
    Optional<Economy> findByName(String name);

    List<Economy> findAllByNameIn(Collection<String> strings);
}
