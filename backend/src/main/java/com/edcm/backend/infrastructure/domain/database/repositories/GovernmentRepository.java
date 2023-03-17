package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Government;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GovernmentRepository extends JpaRepository<Government, Long> {
    boolean existsByNameOrEddnName(String name, String eddnName);

    Optional<Government> findByNameOrEddbId(String name, Long eddbId);
}
