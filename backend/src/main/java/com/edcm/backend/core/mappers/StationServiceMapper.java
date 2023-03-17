package com.edcm.backend.core.mappers;

import com.edcm.backend.infrastructure.domain.database.entities.StationService;
import com.edcm.backend.infrastructure.domain.database.entities.StationServiceDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StationServiceMapper {
    StationService toEntity(StationServiceDto stationServiceDto);

    StationServiceDto toDto(StationService stationService);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StationService partialUpdate(
            StationServiceDto stationServiceDto,
            @MappingTarget StationService stationService
    );
}
