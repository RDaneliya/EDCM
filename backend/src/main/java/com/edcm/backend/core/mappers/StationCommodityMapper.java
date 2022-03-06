package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationCommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StationCommodityMapper {
    StationCommodity stationCommodityDtoToStationCommodity(StationCommodityDto stationCommodityDto);

    StationCommodityDto stationCommodityToStationCommodityDto(StationCommodity stationCommodity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStationCommodityFromStationCommodityDto(StationCommodityDto stationCommodityDto, @MappingTarget StationCommodity stationCommodity);
}
