package com.edcm.backend.api.bot;

import com.edcm.backend.core.mappers.BotTaskMapper;
import com.edcm.backend.core.services.bot.BotTaskService;
import com.edcm.backend.core.shared.data.BotTaskDto;
import com.edcm.backend.infrastructure.domain.database.repositories.BotTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bot")
@AllArgsConstructor
public class BotInfoEndpoint {
    private final BotTaskService service;
    private final BotTaskMapper mapper;

    @GetMapping("/task/{userId}")
    @ResponseBody
    public BotTaskDto getTask(@PathVariable("userId") Long userId) {
        return service.getTask(userId);
    }

    @PostMapping("/task")
    public void createTask(@RequestBody BotTaskRequest taskRequest){
        var dto = mapper.botTaskRequestToBotTaskDto(taskRequest);
        service.saveTask(dto);
    }
}
