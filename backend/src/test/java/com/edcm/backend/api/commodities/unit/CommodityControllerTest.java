package com.edcm.backend.api.commodities.unit;

import com.edcm.backend.api.commodities.CommoditiesController;
import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.projections.CommodityOverview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CommodityControllerTest {

    @Mock
    CommodityTransactionService commodityTransactionService;

    @InjectMocks
    CommoditiesController controller;

    @Test
    public void testGetCommodities() {
        List<CommodityOverview> listOfCommodities = new ArrayList<>();

        when(commodityTransactionService.getOverview()).thenReturn(listOfCommodities);
        List<CommodityOverview> listOfCommodities1 = controller.getCommodities();
        verify(commodityTransactionService, times(1)).getOverview();
        assertThat(listOfCommodities1).isNotNull();
        assertEquals(listOfCommodities.size(),listOfCommodities1.size());
    }

    @Test
    public void testGetCommodityInfo() {
        Commodity commodity = new Commodity(1L,"Test","Test",new CommodityCategory());
        String commodityName = commodity.getName();
        CommodityOverview commodityOverview = new CommodityOverviewImpl();

        when(commodityTransactionService.getSpecificOverview(commodityName)).thenReturn(commodityOverview);
        CommodityOverview commodityOverview1 = controller.getCommodityInfo(commodityName);
        verify(commodityTransactionService, times(1)).getSpecificOverview(commodityName);
        assertThat(commodityOverview1).isNotNull();
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
