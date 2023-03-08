package com.edcm.backend.core.services.commodity;

import com.edcm.backend.infrastructure.domain.database.entities.Commodity;

import java.util.Collection;
import java.util.List;

public interface CommodityTransactionService {

    Commodity createOrFindCommodity(String eddnName);

    Commodity saveCommodity(Commodity commodity);

    List<Commodity> saveAll(Collection<Commodity> commodities);

    List<Commodity> findAllByEddnName(Collection<String> commodityNames);

    boolean isExistByEddnName(String eddnName);

}
