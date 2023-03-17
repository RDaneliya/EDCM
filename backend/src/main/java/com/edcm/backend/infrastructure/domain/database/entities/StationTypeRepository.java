package com.edcm.backend.infrastructure.domain.database.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationTypeRepository extends JpaRepository<StationType, Long> {
    Optional<StationType> findByType(String type);

}
