package com.edcm.backend.core.shared.data;

import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityOverviewDto implements Serializable {
    private Commodity commodity;
    private Long maxBuyPrice;
    private Long minBuyPrice;
    private Long maxSellPrice;
    private Long minSellPrice;
}
