package com.edcm.backend.core.schedule;

import com.edcm.backend.core.mappers.FactionMapper;
import com.edcm.backend.core.services.factions.FactionsService;
import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.core.shared.data.FactionDto;
import com.edcm.backend.core.shared.data.GovernmentDto;
import com.edcm.backend.core.tools.EddbDataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("factionsCheckService")
public class EddbCheckService {
    private final EddbDataProvider eddbDataProvider;
    private final FactionMapper mapper;

    private final FactionsService service;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFactions() {
        log.debug("Checking factions updates");

        List<FactionDto> factionsInfo = eddbDataProvider
                .getFactions()
                .stream()
                .map(factionDto -> {
                    var allegiance = factionDto.getAllegiance();
                    if (factionDto.getAllegiance().getName() == null) {
                        allegiance = new AllegianceDto(null, "Unknown");
                    }
                    var government = factionDto.getGovernment();
                    if (factionDto.getGovernment().getName() == null) {
                        government = new GovernmentDto(factionDto.getGovernment().getId(), "Unknown");
                    }
                    return new FactionDto(
                            factionDto.getId(),
                            factionDto.getName(),
                            allegiance,
                            government,
                            factionDto.getIsPlayerFaction()
                    );
                })
                .collect(Collectors.toList());

        service.saveFactions(factionsInfo);
        log.info("Faction update finished");
    }
}
