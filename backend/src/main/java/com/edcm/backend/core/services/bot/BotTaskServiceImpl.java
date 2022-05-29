package com.edcm.backend.core.services.bot;

import com.edcm.backend.core.mappers.BotTaskMapper;
import com.edcm.backend.core.shared.data.BotTaskDto;
import com.edcm.backend.infrastructure.domain.database.entities.BotTask;
import com.edcm.backend.infrastructure.domain.database.repositories.BotTaskRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BotTaskServiceImpl implements BotTaskService {
    private final BotTaskRepository botTaskRepository;
    private final CommodityRepository commodityRepository;
    private final BotTaskMapper mapper;

    @Override
    public void saveTask(BotTaskDto dto) {
        var commodity = commodityRepository.getById(dto.getCommodityId());
        botTaskRepository.findByUserId(dto.getUserId())
            .ifPresentOrElse(
                botTask -> {
                    botTask.setCommodity(commodity);
                    botTaskRepository.save(botTask);
                },
                () -> botTaskRepository.save(BotTask.builder()
                    .price(dto.getPrice())
                    .commodity(commodity)
                    .userId(dto.getUserId())
                    .build()));
    }

    @Override
    public BotTaskDto getTask(Long userId) {
        return mapper.botTaskToBotTaskDto(botTaskRepository.getBotTaskByUserId(userId));
    }
}
