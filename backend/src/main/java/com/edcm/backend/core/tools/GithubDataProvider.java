package com.edcm.backend.core.tools;

import com.edcm.backend.core.shared.data.CommodityDto;

import java.util.List;


public interface GithubDataProvider {
    List<CommodityDto> getCommodities();
}
