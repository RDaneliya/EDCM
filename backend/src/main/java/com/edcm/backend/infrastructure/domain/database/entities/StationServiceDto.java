package com.edcm.backend.infrastructure.domain.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link StationService} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationServiceDto implements Serializable {
    private Long id;
    private String name;
}
