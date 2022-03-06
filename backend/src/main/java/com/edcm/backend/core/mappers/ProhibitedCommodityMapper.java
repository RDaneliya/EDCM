package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.ProhibitedCommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProhibitedCommodityMapper {
    ProhibitedCommodity prohibitedCommodityDtoToProhibitedCommodity(ProhibitedCommodityDto prohibitedCommodityDto);

    ProhibitedCommodityDto prohibitedCommodityToProhibitedCommodityDto(ProhibitedCommodity prohibitedCommodity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProhibitedCommodityFromProhibitedCommodityDto(ProhibitedCommodityDto prohibitedCommodityDto, @MappingTarget ProhibitedCommodity prohibitedCommodity);
}
