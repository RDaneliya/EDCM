package com.edcm.backend.api.commodities.unit;

import com.edcm.backend.core.services.category.CategoryTransactionServiceImpl;
import com.edcm.backend.core.services.commodity.CommodityTransactionServiceImpl;
import com.edcm.backend.infrastructure.domain.database.entities.*;
import com.edcm.backend.infrastructure.domain.database.projections.CommodityOverview;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommodityTransactionServiceTest {

    @Mock
    CommodityRepository commodityRepository;
    @Mock
    CategoryTransactionServiceImpl categoryTransactionService;

    @InjectMocks
    CommodityTransactionServiceImpl commodityTransactionService;


    @Test
    public void savedCommoditySuccess() {
        Commodity commodity = new Commodity();
        commodity.setId(1L);
        commodity.setName("Test");
        commodity.setEddnName("Test");
        commodity.setCategory(new CommodityCategory());

        when(commodityRepository.save(any(Commodity.class))).thenReturn(commodity);
        Commodity savedCommodity = commodityTransactionService.saveCommodity(commodity);
        assertThat(savedCommodity.getName()).isNotNull();
    }

    @Test
    public void commoditySavedInDB() {
        Commodity commodity = new Commodity();
        commodity.setId(1L);
        commodity.setName("Test");
        commodity.setEddnName("Test");
        commodity.setCategory(new CommodityCategory());
        List<Commodity> commodityList = new ArrayList<>();
        commodityList.add(commodity);
        List<String> actualNames = commodityList.stream()
                .map(Commodity::getEddnName)
                .collect(Collectors.toList());

        when(commodityRepository.findDistinctByEddnNameInIgnoreCase(actualNames)).thenReturn(commodityList);
        List<Commodity> foundList = commodityTransactionService.findAllByEddnName(actualNames);
        assertThat(foundList.size()).isGreaterThan(0);
    }

    @Test
    public void commodityIsExistTest(){
        Commodity commodity = new Commodity();
        commodity.setId(1L);
        commodity.setName("Test");
        commodity.setEddnName("Test");
        commodity.setCategory(new CommodityCategory());
        String commodityEdnnName = commodity.getEddnName();

        when(commodityRepository.existsByEddnName(commodityEdnnName)).thenReturn(true);
        boolean existCommodity = commodityTransactionService.isExistByEddnName(commodityEdnnName);
        assertThat(existCommodity).isTrue();
    }

    @Test
    public void createOrFindCommodityTest() {
        Commodity commodity = new Commodity(1L,"Test","Test",new CommodityCategory());
        String commodityEdnnName = commodity.getEddnName();

        when(commodityRepository.existsByEddnName(commodityEdnnName)).thenReturn(true);
        when(commodityRepository.getCommodityEntitiesByEddnName(commodityEdnnName)).thenReturn(commodity);
        Commodity commodity1 = commodityTransactionService.createOrFindCommodity(commodityEdnnName);
        assertThat(commodity1).isNotNull();
    }

    @Test
    public void getSpecificOverviewTest() {
        Commodity commodity = new Commodity(1L,"Test","Test",new CommodityCategory());
        String commodityEdnnName = commodity.getEddnName();
        CommodityOverview commodityOverview = new CommodityOverviewImpl();

        when(commodityRepository.getOverviewByName(commodityEdnnName)).thenReturn(commodityOverview);
        CommodityOverview commodityOverview1 = commodityTransactionService.getSpecificOverview(commodityEdnnName);
        assertThat(commodityOverview1).isNotNull();
    }

    @Test
    public void getOverviewTest() {
        List<CommodityOverview> commodityOverviewList = new ArrayList<>();

        when(commodityRepository.getOverview()).thenReturn(commodityOverviewList);
        List<CommodityOverview> commodityOverview1 = commodityTransactionService.getOverview();
        assertThat(commodityOverview1).isNotNull();
        assertEquals(commodityOverviewList.size(),commodityOverview1.size());
    }

    private static class CommodityOverviewImpl implements CommodityOverview{

        @Override
        public Long getCommodityId() {
            return null;
        }

        @Override
        public Long getMaxSellPrice() {
            return null;
        }

        @Override
        public Long getMinBuyPrice() {
            return null;
        }

        @Override
        public String getCommodityName() {
            return null;
        }
    }
}
