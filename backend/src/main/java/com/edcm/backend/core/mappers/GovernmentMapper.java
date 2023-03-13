package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.GovernmentDto;
import com.edcm.backend.infrastructure.domain.database.entities.Government;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GovernmentMapper {
    Government toEntity(GovernmentDto governmentDto);

    GovernmentDto toDto(Government government);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Government partialUpdate(
            GovernmentDto governmentDto,
            @MappingTarget Government government
    );
}
