package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.services.economy.EconomyTransactionService;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import com.edcm.backend.infrastructure.domain.database.entities.System;
import com.edcm.backend.infrastructure.domain.database.repositories.StationCommodityRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.SystemRepository;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.CommodityContent;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnCommodityPayload;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnEconomy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EddnStationCommoditiesServiceImpl implements EddnStationCommoditiesService {
    private final StationCommodityRepository stationCommodityRepository;
    private final StationRepository stationRepository;
    private final EconomyTransactionService economyTransactionService;
    private final SystemRepository systemRepository;
    private final CommodityTransactionService commodityTransactionService;

    @Override
    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Throwable.class)
    public void saveData(EddnCommodityPayload payload) throws DataAccessException {
        var content = payload.getContent();

        System system = systemRepository.findByName(content.getSystemName())
                                        .orElseGet(() -> new System(null, content.getSystemName(), null));

        Optional<Station> stationOptional;

        if (content.getStationName().matches("([A-Z0-9]){3}-([A-Z0-9]){3}")) {
            stationOptional = stationRepository.findByName(content.getStationName());
        } else {
            stationOptional = stationRepository.findByNameAndSystem_Name(
                    content.getStationName(),
                    content.getSystemName()
            );
        }

        Station station = unboxStation(stationOptional, system, content);

        if (!CollectionUtils.isEmpty(content.getCommodities())) {
            initCommodities(content, station);
        }

        if (!CollectionUtils.isEmpty(content.getEconomies())) {
            initEconomies(content, station);
        }

        saveStation(station);
        log.info("Saved station: {}, system: {}", station.getName(), station.getSystem().getName());
    }

    private void saveStation(Station station) {
        try {
            stationRepository.save(station);
        } catch (DataAccessException e) {
            log.error("Error saving entity: {}", station, e);
        }
    }

    private void initEconomies(CommodityContent content, Station station) {
        List<String> zeromqEconomies = content
                .getEconomies()
                .stream()
                .map(EddnEconomy::getName)
                .toList();
        List<Economy> economies = economyTransactionService.findAll(zeromqEconomies);
        station.setEconomies(economies);
    }

    private void initCommodities(CommodityContent content, Station station) {
        List<CommodityDto> commodityDtos = content
                .getCommodities()
                .stream()
                .map(item -> new CommodityDto(null, item.getEddnName(), item.getEddnName(), null))
                .toList();

        Map<String, Commodity> commodityMap = commodityTransactionService
                .saveAll(commodityDtos)
                .stream()
                .collect(Collectors.toMap(Commodity::getEddnName, item -> item));


        List<StationCommodity> stationCommodities = content
                .getCommodities()
                .stream()
                .map(item -> {
                    var commodity = commodityMap.get(item.getEddnName());
                    return new StationCommodity(
                            null,
                            station,
                            commodity,
                            item.getStock(),
                            item.getDemand(),
                            item.getBuyPrice(),
                            item.getSellPrice()
                    );
                })
                .toList();

        station.addCommodities(stationCommodities);
    }

    private Station unboxStation(Optional<Station> optionalStation, System system, CommodityContent content) {
        return optionalStation
                .map(stationEntity -> {
                    stationCommodityRepository.deleteAll(stationEntity.getCommodities());

                    return stationEntity;
                })
                .orElseGet(() -> new Station(
                        content.getMarketId(),
                        content.getStationName(),
                        system
                ));
    }
}
