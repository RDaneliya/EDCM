package com.edcm.backend.core.shared.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemDto implements Serializable {
    private Long id;
    private String name;
    private CoordinatesDto coordinates;
    private AllegianceDto allegiance;
    private EconomyDto economy;

    /**
     * A DTO for the {@link com.edcm.backend.infrastructure.domain.database.entities.Coordinates} entity
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoordinatesDto implements Serializable {
        private Double x;
        private Double y;
        private Double z;
    }
}
