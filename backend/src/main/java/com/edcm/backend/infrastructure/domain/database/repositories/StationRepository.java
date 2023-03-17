package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> getByName(@NonNull String name);

    Optional<Station> findByName(@NonNull String name);

    Optional<Station> findByNameAndSystem_Name(@NonNull String stationName, @NonNull String systemName);

    Optional<Station> findByMarketId(Long marketId);

}
