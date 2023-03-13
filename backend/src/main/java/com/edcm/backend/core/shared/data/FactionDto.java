package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.edcm.backend.infrastructure.domain.database.entities.Faction} entity
 */
@Data
public class FactionDto implements Serializable {
    private final Long id;
    private final String name;
    private final AllegianceDto allegiance;
    private final GovernmentDto government;
    private final Boolean isPlayerFaction;
}
