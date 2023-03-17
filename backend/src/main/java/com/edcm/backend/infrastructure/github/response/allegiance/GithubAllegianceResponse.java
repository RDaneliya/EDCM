package com.edcm.backend.infrastructure.github.response.allegiance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubAllegianceResponse {

    private List<AllegianceResponseItem> githubAllegiancesItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AllegianceResponseItem {
        private String id;
        private String name;
    }
}
