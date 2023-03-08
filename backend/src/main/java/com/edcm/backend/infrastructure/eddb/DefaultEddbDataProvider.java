package com.edcm.backend.infrastructure.eddb;

import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.core.shared.data.FactionDto;
import com.edcm.backend.core.shared.data.GovernmentDto;
import com.edcm.backend.core.tools.EddbDataProvider;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DefaultEddbDataProvider implements EddbDataProvider {
    private final EddbOperations eddbOperations;

    @Override
    public List<FactionDto> getFactions() {
        return eddbOperations
                .getFactions()
                .getFactionItems()
                .stream()
                .map(item -> {
                    AllegianceDto allegianceDto = new AllegianceDto(item.getAllegianceId(), item.getAllegiance());
                    GovernmentDto governmentDto = new GovernmentDto(item.getGovernmentId(), item.getGovernment());
                    return new FactionDto(
                            item.getId(),
                            item.getName(),
                            allegianceDto,
                            governmentDto,
                            item.getIsPlayerFaction()
                    );
                })
                .toList();
    }
}
