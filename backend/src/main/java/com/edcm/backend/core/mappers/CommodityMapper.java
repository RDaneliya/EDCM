package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItemWithEddn;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommodityMapper {
    Commodity commodityDtoToCommodity(CommodityDto commodityDto);

    CommodityDto commodityToCommodityDto(Commodity commodity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommodityFromCommodityDto(CommodityDto commodityDto, @MappingTarget Commodity commodity);

    @Mapping(source = "category", target = "category.name")
    CommodityDto githubItemToCommodityDto(GithubCommodityItemWithEddn githubItem);

}
