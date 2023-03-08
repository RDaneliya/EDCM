package com.edcm.backend.core.services.commodity;

import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnCommodityPayload;

public interface EddnStationCommoditiesService {
    void saveData(EddnCommodityPayload payload);

}
