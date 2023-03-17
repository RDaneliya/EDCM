package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.edcm.backend.infrastructure.domain.database.entities.Government} entity
 */
@Data
public class GovernmentDto implements Serializable {
    private final Long id;
    private final String eddnName;
    private final Long eddbId;
    private final String name;
}
