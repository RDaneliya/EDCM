package com.edcm.backend.infrastructure.github.response.economy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubEconomyResponse {
    private List<GithubEconomyItem> githubEconomyItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GithubEconomyItem {
        private String id;
        private String name;
    }
}
