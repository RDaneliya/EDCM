package com.edcm.backend.core.shared.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationCommodityDto implements Serializable {
    private Long id;
    private CommodityDto commodity;
    private Long stock;
    private Long demand;
    private Long buyPrice;
    private Long sellPrice;
}
