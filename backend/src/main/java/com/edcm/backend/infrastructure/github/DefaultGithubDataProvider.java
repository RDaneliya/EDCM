package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.mappers.commodity.CommodityMapper;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.core.tools.GithubDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGithubDataProvider implements GithubDataProvider {
    private final GithubOperations githubOperations;
    private final CommodityMapper mapper;

    @Override
    public List<CommodityDto> getCommodities() {

        return githubOperations.getCommodities()
            .getItems()
            .entrySet()
            .stream()
            .map(item -> new GithubCommodityItemWithEddn(
                item.getValue().getId(),
                item.getValue().getCategory(),
                item.getValue().getName(),
                item.getKey()))
            .map(mapper::githubItemToCommodityDto)
            .toList();
    }
}
