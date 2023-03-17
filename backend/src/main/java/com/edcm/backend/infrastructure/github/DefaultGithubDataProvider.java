package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.mappers.CommodityMapper;
import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.core.shared.data.GovernmentDto;
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
                          .map(item -> {
                              var category = new CommodityCategoryDto(null, item.getCategory());
                              return new CommodityDto(item.getId(), item.getName(), item.getSymbol(), category);
                          })
                          .toList();

    }

    @Override
    public List<EconomyDto> getEconomies() {
        return githubOperations.getEconomies()
                               .getGithubEconomyItems()
                               .stream()
                               .map(item -> new EconomyDto(null, item.getId(), item.getName()))
                               .toList();
    }

    @Override
    public List<AllegianceDto> getAllegiances() {
        return githubOperations.getAllegiances()
                               .getGithubAllegiancesItems()
                               .stream()
                               .map(item -> new AllegianceDto(null, item.getId(), null, item.getName()))
                               .toList();
    }

    @Override
    public List<GovernmentDto> getGovernments() {
        return githubOperations.getGovernments()
                               .getGithubGovernmentItems()
                               .stream()
                               .map(item -> new GovernmentDto(null, item.getId(), null, item.getName()))
                               .toList();
    }
}
