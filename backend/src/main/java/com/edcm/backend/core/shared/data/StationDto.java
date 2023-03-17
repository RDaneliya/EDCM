package com.edcm.backend.core.shared.data;

import com.edcm.backend.infrastructure.domain.database.entities.FactionStateDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationServiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationDto implements Serializable {
    private Long id;
    private Long marketId;
    private String name;
    private SystemDto system;
    private StationTypeDto stationTypeDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<StationCommodityDto> commodities;
    private Set<EconomyDto> economies;
    private Set<StationServiceDto> stationServices;
    private FactionStateDto factionState;
}
