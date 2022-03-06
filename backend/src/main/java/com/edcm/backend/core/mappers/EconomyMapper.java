package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EconomyMapper {
    Economy economyDtoToEconomy(EconomyDto economyDto);

    EconomyDto economyToEconomyDto(Economy economy);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEconomyFromEconomyDto(EconomyDto economyDto, @MappingTarget Economy economy);
}
