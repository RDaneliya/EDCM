package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;

import java.util.Collection;
import java.util.List;

public interface CommodityTransactionService {

    List<Commodity> saveAll(Collection<CommodityDto> commodities);

    Commodity save(CommodityDto commodityDto);

}
