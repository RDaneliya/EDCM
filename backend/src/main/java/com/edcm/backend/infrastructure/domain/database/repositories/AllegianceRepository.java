package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Allegiance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllegianceRepository extends JpaRepository<Allegiance, Long> {
}
