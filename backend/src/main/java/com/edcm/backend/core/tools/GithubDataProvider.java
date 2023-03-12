package com.edcm.backend.core.tools;

import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.core.shared.data.EconomyDto;

import java.util.List;


public interface GithubDataProvider {
    List<CommodityDto> getCommodities();

    List<EconomyDto> getEconomies();
}
