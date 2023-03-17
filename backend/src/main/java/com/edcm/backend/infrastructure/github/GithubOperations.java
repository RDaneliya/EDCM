package com.edcm.backend.infrastructure.github;

import com.edcm.backend.infrastructure.github.response.allegiance.GithubAllegianceResponse;
import com.edcm.backend.infrastructure.github.response.commodity.GithubCommodityResponse;
import com.edcm.backend.infrastructure.github.response.economy.GithubEconomyResponse;
import com.edcm.backend.infrastructure.github.response.government.GithubGovernmentResponse;

public interface GithubOperations {
    GithubCommodityResponse getRegularCommodities();
    GithubCommodityResponse getRareCommodities();
    GithubEconomyResponse getEconomies();
    GithubGovernmentResponse getGovernments();
    GithubAllegianceResponse getAllegiances();
}
