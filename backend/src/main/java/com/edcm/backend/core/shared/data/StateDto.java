package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.edcm.backend.infrastructure.domain.database.entities.State} entity
 */
@Data
public class StateDto implements Serializable {
    private final Long id;
    private final String eddnId;
    private final Long eddbId;
    private final String name;
}