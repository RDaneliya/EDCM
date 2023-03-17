package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommodityMapper {
    Commodity toEntity(CommodityDto commodityDto);

    CommodityDto toDto(Commodity commodity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Commodity partialUpdate(
            CommodityDto commodityDto,
            @MappingTarget Commodity commodity
    );
}
