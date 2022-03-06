package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StationMapper {
    Station stationDtoToStation(StationDto stationDto);

    StationDto stationToStationDto(Station station);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStationFromStationDto(StationDto stationDto, @MappingTarget Station station);
}
