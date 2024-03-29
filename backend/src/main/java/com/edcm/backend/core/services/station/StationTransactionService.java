package com.edcm.backend.core.services.station;

import com.edcm.backend.core.shared.data.StationDto;

public interface StationTransactionService {

    StationDto createOrFindCarrier(String code);

    StationDto saveStation(StationDto station);
}
