package com.edcm.backend.infrastructure.github.response.commodity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubCommodityResponse {
    private List<GithubCommodityItem> items;
}
