package com.edcm.backend.infrastructure.github;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubCommodityResponse {
    private Map<String, GithubCommodityItem> items;
}
