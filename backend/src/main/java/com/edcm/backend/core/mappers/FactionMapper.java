package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.FactionDto;
import com.edcm.backend.infrastructure.domain.database.entities.Faction;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FactionMapper {
    Faction toEntity(FactionDto factionDto);

    FactionDto toDto(Faction faction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Faction partialUpdate(
            FactionDto factionDto,
            @MappingTarget Faction faction
    );
}
