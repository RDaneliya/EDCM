package com.edcm.backend.infrastructure.domain.database.projections;

public interface CommodityOverview {

    Long getCommodityId();

    Long getMaxSellPrice();

    Long getMinBuyPrice();

    String getCommodityName();

    String getCommodityCategory();

}
