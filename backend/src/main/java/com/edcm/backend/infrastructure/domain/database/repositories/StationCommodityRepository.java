package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationCommodityRepository extends JpaRepository<StationCommodity, Long> {
    void deleteByStation(Station station);

}
