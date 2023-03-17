package com.edcm.backend.core.scheduled;

import com.edcm.backend.core.mappers.FactionMapper;
import com.edcm.backend.core.services.faction.FactionsService;
import com.edcm.backend.core.tools.EddbDataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("factionsCheckService")
public class EddbCheckService {
    private final EddbDataProvider eddbDataProvider;
    private final FactionMapper mapper;

    private final FactionsService service;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateFactions() {
        log.info("Checking factions updates");

        log.info("Faction update finished");
    }

    @Transactional
    public void updateSystems() {

    }
}
