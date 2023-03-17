package com.edcm.backend.core.services.economy;

import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.infrastructure.domain.database.entities.Economy;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EconomyTransactionService {
    Economy saveEconomy(EconomyDto economyDto);
    List<Economy> saveAll(Collection<EconomyDto> economyDtos);

    Set<Economy> findAll(Collection<String> names);
}
