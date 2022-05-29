package com.edcm.backend.core.mappers;

import com.edcm.backend.api.bot.BotTaskRequest;
import com.edcm.backend.core.shared.data.BotTaskDto;
import com.edcm.backend.infrastructure.domain.database.entities.BotTask;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BotTaskMapper {

    @Mapping(source = "commodity.id", target = "commodityId")
    BotTaskDto botTaskToBotTaskDto(BotTask botTask);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBotTaskFromBotTaskDto(BotTaskDto botTaskDto, @MappingTarget BotTask botTask);

    BotTaskDto botTaskRequestToBotTaskDto(BotTaskRequest botTaskRequest);

}
