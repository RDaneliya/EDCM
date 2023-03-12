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
}
