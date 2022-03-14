package com.edcm.backend.core.shared.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityOverviewDto implements Serializable {
    private Long commodityId;
    private String commodityName;
    private String commodityCategory;
    private Long minBuyPrice;
    private Long maxSellPrice;
}
