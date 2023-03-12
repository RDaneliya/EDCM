package com.edcm.backend.core.mappers.commodity;

import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.github.response.commodity.GithubCommodityItem;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommodityMapperImpl implements CommodityMapper {
    private final CommodityCategoryMapper commodityCategoryMapper;

    @Override
    public Commodity toEntity(@NotNull CommodityDto commodityDto) {
        var category = commodityCategoryMapper.toEntity(commodityDto.getCategory());
        return new Commodity(commodityDto.getId(), commodityDto.getName(), commodityDto.getEddnName(), category);
    }

    @Override
    public CommodityDto toDto(@NotNull Commodity commodity) {
        var categoryDto = commodityCategoryMapper.toDto(commodity.getCategory());
        return new CommodityDto(commodity.getId(), commodity.getName(),commodity.getEddnName(), categoryDto);
    }

    @Override
    public CommodityDto githubItemToCommodityDto(GithubCommodityItem githubItem) {
        CommodityCategoryDto commodityCategoryDto = new CommodityCategoryDto(null, githubItem.getCategory());
        Long id = Long.parseLong(githubItem.getId());
        return new CommodityDto(id, githubItem.getName(), githubItem.getSymbol(), commodityCategoryDto);
    }
}
