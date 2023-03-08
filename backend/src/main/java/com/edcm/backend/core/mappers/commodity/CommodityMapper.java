package com.edcm.backend.core.mappers.commodity;

import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.github.GithubCommodityItemWithEddn;

public interface CommodityMapper {
    Commodity toEntity(CommodityDto commodityDto);

    CommodityDto toDto(Commodity commodity);

    CommodityDto githubItemToCommodityDto(GithubCommodityItemWithEddn githubItem);

}
