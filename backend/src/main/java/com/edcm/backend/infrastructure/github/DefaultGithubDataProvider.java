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
        var commodities = githubOperations.getRegularCommodities().getItems();
        var rareCommodities = githubOperations.getRareCommodities().getItems();

        commodities.addAll(rareCommodities);

        return commodities.stream()
                          .peek(item -> item.setSymbol(item.getSymbol().toLowerCase()))
                          .map(mapper::githubItemToCommodityDto)
                          .toList();

    }
}
