package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Government;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GovernmentRepository extends JpaRepository<Government, Long> {
}
