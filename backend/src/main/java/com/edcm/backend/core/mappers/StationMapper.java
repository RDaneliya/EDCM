package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationBasicInfoDto;
import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.projections.StationBasicInfo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StationMapper {
    Station stationDtoToStation(StationDto stationDto);

    StationDto stationToStationDto(Station station);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStationFromStationDto(StationDto stationDto, @MappingTarget Station station);

    @Mappings({
        @Mapping(source = "system.name", target = "systemName"),
        @Mapping(source = "system.id", target = "systemId")
    })
    StationBasicInfoDto projectionToStationBasicInfoDto(StationBasicInfo projection);
}
