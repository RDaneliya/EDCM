package com.edcm.backend.api.commodities.unit;

import com.edcm.backend.core.mappers.CommodityCategoryMapper;
import com.edcm.backend.core.mappers.CommodityMapper;
import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItemWithEddn;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommodityMapperTest {

    private CommodityMapper commodityMapper = Mappers.getMapper(CommodityMapper.class);
    private CommodityCategoryMapper commodityCategoryMapper = Mappers.getMapper(CommodityCategoryMapper.class);

    @Test
    public void commodityToDtoTest() {
        Commodity commodity = new Commodity();
        commodity.setId(1L);
        commodity.setName("Test");
        commodity.setEddnName("Test");
        commodity.setCategory(new CommodityCategory());

        CommodityCategoryDto commodityCategoryDto = commodityCategoryMapper.commodityToCommodityDto(commodity.getCategory());
        CommodityDto commodityDto = commodityMapper.commodityToCommodityDto(commodity);

        assertEquals(commodity.getId(),commodityDto.getId());
        assertEquals(commodity.getName(),commodityDto.getName());
        assertEquals(commodity.getEddnName(),commodityDto.getEddnName());
        assertEquals(commodityCategoryDto, commodityDto.getCategory());
    }

    @Test
    public void commodityDtoToCommodityTest() {
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setId(1L);
        commodityDto.setName("Test");
        commodityDto.setEddnName("Test");
        commodityDto.setCategory(new CommodityCategoryDto());

        CommodityCategory commodityCategory = commodityCategoryMapper.commodityDtoToCommodity(commodityDto.getCategory());
        Commodity commodity = commodityMapper.commodityDtoToCommodity(commodityDto);

        assertEquals(commodity.getId(),commodityDto.getId());
        assertEquals(commodity.getName(),commodityDto.getName());
        assertEquals(commodity.getEddnName(),commodityDto.getEddnName());
        assertEquals(commodityCategory.toString(), commodity.getCategory().toString());
    }

    @Test
    public void updateCommodityTest() {
        Commodity commodity = new Commodity();
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setId(1L);
        commodityDto.setName("Test");
        commodityDto.setEddnName("Test");
        commodityDto.setCategory(new CommodityCategoryDto());

        commodityMapper.updateCommodityFromCommodityDto(commodityDto,commodity);
        CommodityCategory commodityCategory = commodityCategoryMapper.commodityDtoToCommodity(commodityDto.getCategory());

        assertEquals(commodity.getId(),commodityDto.getId());
        assertEquals(commodity.getName(),commodityDto.getName());
        assertEquals(commodity.getEddnName(),commodityDto.getEddnName());
        assertEquals(commodityCategory.toString(), commodity.getCategory().toString());
    }

    @Test
    public void githubItemToCommodityDtoTest() {
        GithubCommodityItemWithEddn githubItem = new GithubCommodityItemWithEddn();
        githubItem.setId("1");
        githubItem.setName("Test");
        githubItem.setEddnName("Test");
        githubItem.setCategory("TestCategory");

        CommodityDto commodityDto = commodityMapper.githubItemToCommodityDto(githubItem);

        assertEquals(commodityDto.getId().toString(),githubItem.getId());
        assertEquals(commodityDto.getName(),githubItem.getName());
        assertEquals(commodityDto.getEddnName(),githubItem.getEddnName());
        assertEquals(commodityDto.getCategory().getName(), githubItem.getCategory());
    }
}
