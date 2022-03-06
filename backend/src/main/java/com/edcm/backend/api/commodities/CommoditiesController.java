package com.edcm.backend.api.commodities;

import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.core.shared.data.CommodityOverviewDto;
import com.edcm.backend.infrastructure.domain.database.projections.CommodityOverview;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/commodity")
@AllArgsConstructor
public class CommoditiesController {
    private final CommodityTransactionService commodityTransactionService;

    @GetMapping("/overview")
    @ResponseBody
    public List<CommodityOverview> getCommodities(){
         return commodityTransactionService.getOverview();
    }
}
