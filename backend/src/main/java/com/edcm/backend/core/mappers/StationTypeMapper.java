package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationTypeDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StationTypeMapper {
    StationType toEntity(StationTypeDto stationTypeDto);

    StationTypeDto toDto(StationType stationType);

}
