package com.edcm.backend.core.services.station;

import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.projections.StationBasicInfo;

import java.util.List;

public interface StationTransactionService {
    Station createOrFindStation(String name, String systemName);

    Station createOfFindCarrier(String code);

    Station saveStation(Station station);

    List<StationBasicInfo> findByNameStartsWith(String stationName);
}
