package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionRepository extends JpaRepository<Faction, Long> {
}
