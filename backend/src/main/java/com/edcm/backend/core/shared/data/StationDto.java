package com.edcm.backend.core.shared.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationDto implements Serializable {
    private Long id;
    private String name;
    private SystemDto system;
    @Builder.Default
    private List<StationCommodityDto> commodities = new ArrayList<>();
    @Builder.Default
    private List<EconomyDto> economies = new ArrayList<>();
    private StationTypeDto stationTypeDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
