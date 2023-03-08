package com.edcm.backend.core.mappers;


import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.entities.Station;

public interface StationMapper {
    StationDto toDto(Station entity);
    Station toEntity(StationDto dto);
}
