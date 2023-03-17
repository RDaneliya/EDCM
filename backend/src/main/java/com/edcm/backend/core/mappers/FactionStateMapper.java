package com.edcm.backend.core.mappers;

import com.edcm.backend.infrastructure.domain.database.entities.FactionState;
import com.edcm.backend.infrastructure.domain.database.entities.FactionStateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FactionStateMapper {
    FactionState toEntity(FactionStateDto factionStateDto);

    FactionStateDto toDto(FactionState factionState);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FactionState partialUpdate(
            FactionStateDto factionStateDto,
            @MappingTarget FactionState factionState
    );
}
