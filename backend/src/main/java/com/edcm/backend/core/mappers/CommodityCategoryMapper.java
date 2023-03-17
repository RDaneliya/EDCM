package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommodityCategoryMapper {
    CommodityCategory toEntity(CommodityCategoryDto commodityCategoryDto);

    CommodityCategoryDto toDto(CommodityCategory commodityCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommodityCategory partialUpdate(
            CommodityCategoryDto commodityCategoryDto,
            @MappingTarget CommodityCategory commodityCategory
    );
}
