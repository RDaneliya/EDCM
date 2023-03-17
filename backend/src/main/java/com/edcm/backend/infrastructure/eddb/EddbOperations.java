package com.edcm.backend.infrastructure.eddb;

import com.edcm.backend.infrastructure.eddb.factions.EddbFactionsResponse;

public interface EddbOperations {
    EddbFactionsResponse getFactions();
}
