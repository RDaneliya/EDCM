package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.SystemDto;
import com.edcm.backend.infrastructure.domain.database.entities.System;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SystemMapper {
    System toEntity(SystemDto systemDto);

    SystemDto toDto(System system);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    System partialUpdate(
            SystemDto systemDto,
            @MappingTarget System system
    );
}
