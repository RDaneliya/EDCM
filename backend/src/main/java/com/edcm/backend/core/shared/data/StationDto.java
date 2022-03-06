package com.edcm.backend.core.shared.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDto implements Serializable {
    private Long id;
    private String name;
    private List<ProhibitedCommodityDto> prohibited = new ArrayList<>();
    private List<StationCommodityDto> commodities = new ArrayList<>();
    private List<StationEconomyDto> economies = new ArrayList<>();
}
