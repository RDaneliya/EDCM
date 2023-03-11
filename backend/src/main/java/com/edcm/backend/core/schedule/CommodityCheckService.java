package com.edcm.backend.core.schedule;

import com.edcm.backend.core.services.category.CategoryTransactionService;
import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("commodityCheckService")
public class CommodityCheckService {
    private final GithubDataProvider dataProvider;
    private final CommodityTransactionService commodityTransactionService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${scheduled.github.cron}")
    public void updateCommodities() {
        log.info("Checking commodities updates");

        var commoditiesInfo = dataProvider.getCommodities();
        commodityTransactionService.saveAll(commoditiesInfo);
        log.info("Commodity update finished");
    }
}
