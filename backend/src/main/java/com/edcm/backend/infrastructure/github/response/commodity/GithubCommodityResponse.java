package com.edcm.backend.infrastructure.github.response.commodity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubCommodityResponse {
    private List<GithubCommodityItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GithubCommodityItem {
        private Long id;
        private String symbol;
        private String category;
        private String name;
    }
}
