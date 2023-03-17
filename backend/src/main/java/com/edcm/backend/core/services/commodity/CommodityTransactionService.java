package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommodityTransactionService {

    List<Commodity> saveAll(Collection<CommodityDto> commodities);

    Commodity save(CommodityDto commodityDto);

    Map<String, Commodity> findAll(List<String> names);
}
