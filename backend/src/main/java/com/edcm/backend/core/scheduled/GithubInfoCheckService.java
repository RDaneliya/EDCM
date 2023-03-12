package com.edcm.backend.core.scheduled;

import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.core.services.economy.EconomyTransactionService;
import com.edcm.backend.core.tools.GithubDataProvider;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("githubInfoCheckService")
public class GithubInfoCheckService {
    private final GithubDataProvider dataProvider;
    private final CommodityTransactionService commodityTransactionService;
    private final EconomyTransactionService economyTransactionService;

    @PostConstruct
    public void updateAll(){
        updateCommodities();
        updateEconomies();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${scheduled.github.cron}")
    public void updateCommodities() {
        log.info("Checking commodities updates");

        var commoditiesInfo = dataProvider.getCommodities();
        commodityTransactionService.saveAll(commoditiesInfo);
        log.info("Commodity update finished");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${scheduled.github.cron}")
    public void updateEconomies() {
        log.info("Checking economies update");
        economyTransactionService.saveAll(dataProvider.getEconomies());
        log.info("Economy update finished");
    }
}
