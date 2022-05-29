package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.BotTask;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BotTaskRepository extends JpaRepository<BotTask, Long> {

    BotTask getBotTaskByUserId(@NonNull Long userId);

    Optional<BotTask> findByUserId(@NonNull Long userId);

}
