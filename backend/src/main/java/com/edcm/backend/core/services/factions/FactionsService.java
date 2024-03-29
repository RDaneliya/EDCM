package com.edcm.backend.core.services.factions;

import com.edcm.backend.core.shared.data.FactionDto;

import java.util.List;

public interface FactionsService {
    FactionDto saveFaction(FactionDto factionDto);

    void saveFactions(List<FactionDto> factionDtos);
}
