package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.StationService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationServiceRepository extends JpaRepository<StationService, Long> {
}
