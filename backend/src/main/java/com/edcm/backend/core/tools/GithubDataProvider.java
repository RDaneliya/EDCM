package com.edcm.backend.core.tools;

import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.core.shared.data.GovernmentDto;

import java.util.List;


public interface GithubDataProvider {
    List<CommodityDto> getCommodities();

    List<EconomyDto> getEconomies();

    List<AllegianceDto> getAllegiances();

    List<GovernmentDto> getGovernments();
}
