package com.edcm.backend.core.mappers.commodity;

import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommodityCategoryMapper {

    CommodityCategory toEntity(CommodityCategoryDto commodityDto);

    CommodityCategoryDto toDto(CommodityCategory commodity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommodityCategoryFromCommodityDto(
        CommodityCategoryDto commodityDto,
        @MappingTarget CommodityCategory commodity);

}
