package com.edcm.backend.infrastructure.github;

public interface GithubOperations {
    GithubCommodityResponse getRegularCommodities();
    GithubCommodityResponse getRareCommodities();
}
