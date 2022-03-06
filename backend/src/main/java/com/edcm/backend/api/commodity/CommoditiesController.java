package com.edcm.backend.api.commodity;

import com.edcm.backend.core.mappers.CommodityMapper;
import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.core.shared.data.CommodityOverviewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/commodity")
@AllArgsConstructor
public class CommoditiesController {
    private final CommodityTransactionService commodityTransactionService;
    private final CommodityMapper mapper;

    @GetMapping("/overview")
    @ResponseBody
    public List<CommodityOverviewDto> getCommodities() {
        return commodityTransactionService.getOverview()
            .stream()
            .map(mapper::commodityOverviewProjectionToCommodityOverviewDto)
            .collect(Collectors.toList());
    }
}
