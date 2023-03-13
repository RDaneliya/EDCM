package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.edcm.backend.infrastructure.domain.database.entities.Allegiance} entity
 */
@Data
public class AllegianceDto implements Serializable {
    private final Long id;
    private final String name;
}
