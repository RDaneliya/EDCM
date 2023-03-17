package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StationCommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StationCommodityMapper {
    StationCommodity toEntity(StationCommodityDto stationCommodityDto);

    StationCommodityDto toDto(StationCommodity stationCommodity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StationCommodity partialUpdate(
            StationCommodityDto stationCommodityDto,
            @MappingTarget StationCommodity stationCommodity
    );
}
