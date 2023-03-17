package com.edcm.backend.infrastructure.github.response.government;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubGovernmentResponse {
    List<GovernmentResponseItem> githubGovernmentItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GovernmentResponseItem {
        private String id;
        private String name;
    }
}
