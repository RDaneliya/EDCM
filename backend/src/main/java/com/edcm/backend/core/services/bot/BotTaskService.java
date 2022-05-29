package com.edcm.backend.core.services.bot;

import com.edcm.backend.core.shared.data.BotTaskDto;

public interface BotTaskService {
    void saveTask(BotTaskDto dto);

    BotTaskDto getTask(Long userId);
}
