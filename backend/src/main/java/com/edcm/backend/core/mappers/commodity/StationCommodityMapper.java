package com.edcm.backend.core.mappers.commodity;

import com.edcm.backend.core.shared.data.StationCommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;

public interface StationCommodityMapper {
    StationCommodity toEntity(StationCommodityDto stationCommodityDto);

    StationCommodityDto toDto(StationCommodity stationCommodity);

}
