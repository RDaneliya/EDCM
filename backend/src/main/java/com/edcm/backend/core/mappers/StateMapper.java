package com.edcm.backend.core.mappers;

import com.edcm.backend.core.shared.data.StateDto;
import com.edcm.backend.infrastructure.domain.database.entities.State;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateMapper {
    State toEntity(StateDto stateDto);

    StateDto toDto(State state);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    State partialUpdate(
            StateDto stateDto,
            @MappingTarget
            State state
    );
}
