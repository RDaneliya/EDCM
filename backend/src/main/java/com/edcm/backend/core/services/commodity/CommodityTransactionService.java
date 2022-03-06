package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.shared.data.CommodityOverviewDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.projections.CommodityOverview;

import java.util.Collection;
import java.util.List;

public interface CommodityTransactionService {

    Commodity createOrFindCommodity(String eddnName);

    Commodity saveCommodity(Commodity commodity);

    List<Commodity> findAllByEddnName(Collection<String> commodityNames);

    boolean isExistByEddnName(String eddnName);

    List<CommodityOverview> getOverview();
}
