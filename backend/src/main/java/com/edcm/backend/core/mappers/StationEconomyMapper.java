package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationEconomyDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationEconomy;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StationEconomyMapper {
    StationEconomy stationEconomyDtoToStationEconomy(StationEconomyDto stationEconomyDto);

    StationEconomyDto stationEconomyToStationEconomyDto(StationEconomy stationEconomy);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStationEconomyFromStationEconomyDto(StationEconomyDto stationEconomyDto, @MappingTarget StationEconomy stationEconomy);
}
