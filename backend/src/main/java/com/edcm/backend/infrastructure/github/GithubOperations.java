package com.edcm.backend.infrastructure.github;

import com.edcm.backend.infrastructure.github.response.commodity.GithubCommodityResponse;
import com.edcm.backend.infrastructure.github.response.economy.GithubEconomyResponse;

public interface GithubOperations {
    GithubCommodityResponse getRegularCommodities();
    GithubCommodityResponse getRareCommodities();
    GithubEconomyResponse getEconomies();
}
