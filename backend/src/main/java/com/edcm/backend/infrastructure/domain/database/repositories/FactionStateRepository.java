package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.FactionState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionStateRepository extends JpaRepository<FactionState, Long> {
}
