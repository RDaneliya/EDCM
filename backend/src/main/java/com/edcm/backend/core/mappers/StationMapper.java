package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StationMapper {
    Station toEntity(StationDto stationDto);

    StationDto toDto(Station station);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Station partialUpdate(
            StationDto stationDto,
            @MappingTarget Station station
    );
}
