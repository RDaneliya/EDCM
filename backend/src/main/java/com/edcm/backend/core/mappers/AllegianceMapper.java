package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.infrastructure.domain.database.entities.Allegiance;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AllegianceMapper {
    Allegiance toEntity(AllegianceDto allegianceDto);

    AllegianceDto toDto(Allegiance allegiance);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Allegiance partialUpdate(
            AllegianceDto allegianceDto,
            @MappingTarget Allegiance allegiance
    );
}
