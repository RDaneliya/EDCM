package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationTypeDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StationTypeMapper {
    StationType toEntity(StationTypeDto stationTypeDto);

    StationTypeDto toDto(StationType stationType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StationType partialUpdate(
            StationTypeDto stationTypeDto,
            @MappingTarget StationType stationType
    );
}
