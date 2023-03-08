package com.edcm.backend.core.services.commodity;

import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import com.edcm.backend.infrastructure.domain.database.entities.System;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityCategoryRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.EconomyRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationCommodityRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.SystemRepository;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.CommodityContent;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnCommodity;
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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EddnStationCommoditiesServiceImpl implements EddnStationCommoditiesService {
    private final StationCommodityRepository stationCommodityRepository;
    private final StationRepository stationRepository;
    private final EconomyRepository economyRepository;
    private final CommodityCategoryRepository commodityCategoryRepository;
    private final CommodityRepository commodityRepository;
    private final SystemRepository systemRepository;

    @Override
    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Throwable.class)
    public void saveData(EddnCommodityPayload payload) throws DataAccessException {
        var content = payload.getContent();

        System system = systemRepository.findByName(content.getSystemName())
                                        .orElseGet(() -> new System(null, content.getSystemName(), null));

        Station station = stationRepository
                .findByNameAndSystem_Name(content.getStationName(), content.getSystemName())
                .map(stationEntity -> {
                    stationCommodityRepository.deleteAll(stationEntity.getCommodities());
                    return stationEntity;
                })
                .orElseGet(() -> new Station(
                        content.getMarketId(),
                        content.getStationName(),
                        system
                ));

        if(station.isCarrier()){
            stationRepository.deleteAll(stationRepository.getByName(station.getName()));
        }

        if (!CollectionUtils.isEmpty(content.getCommodities())) {
            initCommodities(content, station);
        }

        if (!CollectionUtils.isEmpty(content.getEconomies())) {
            initEconomies(content, station);
        }

        saveStation(station);
        log.debug("Saved station: {}, system: {}", station.getName(), station.getSystem().getName());
    }

    private Station saveStation(Station station) {
        try {
            return stationRepository.save(station);
        } catch (DataAccessException e) {
            log.error("Error saving entity: {}", station, e);
        }
        return station;
    }

    private void initEconomies(CommodityContent content, Station station) {
        Set<String> zeromqEconomies = content
                .getEconomies()
                .stream()
                .map(EddnEconomy::getName)
                .collect(Collectors.toSet());
        var persistingEconomies = economyRepository.findAll()
                                                   .stream()
                                                   .filter(economy -> zeromqEconomies.contains(economy.getName()))
                                                   .collect(Collectors.toSet());
        station.setEconomies(persistingEconomies);

        if (zeromqEconomies.size() > persistingEconomies.size()) {
            var persistingNames = persistingEconomies.stream()
                                                     .map(Economy::getName)
                                                     .collect(Collectors.toSet());
            zeromqEconomies.removeAll(persistingNames);
            var newEntities = zeromqEconomies.stream()
                           .map(name -> new Economy(null, name))
                           .collect(Collectors.toSet());
            newEntities = new HashSet<>(economyRepository.saveAll(newEntities));
            station.getEconomies().addAll(newEntities);
        }
    }

    private void initCommodities(CommodityContent content, Station station) {
        List<String> zeroMQCommodities = content.getCommodities().stream()
                                                .map(EddnCommodity::getEddnName)
                                                .toList();
        Map<String, EddnCommodity> zeroMQCommodityMap = content
                .getCommodities()
                .stream()
                .collect(Collectors.toMap(EddnCommodity::getEddnName, item -> item));

        List<Commodity> commodityEntity = commodityRepository
                .findAllByEddnNameIn(zeroMQCommodities);

        List<StationCommodity> stationCommodities = new java.util.ArrayList<>(commodityRepository
                .findAllByEddnNameIn(zeroMQCommodities)
                .stream()
                .map(commodity -> {
                    var zmqCommodity = zeroMQCommodityMap.get(commodity.getEddnName());
                    return StationCommodity.builder()
                                           .station(station)
                                           .commodity(commodity)
                                           .stock(zmqCommodity.getStock())
                                           .demand(zmqCommodity.getDemand())
                                           .buyPrice(zmqCommodity.getBuyPrice())
                                           .sellPrice(zmqCommodity.getSellPrice())
                                           .build();

                })
                .toList());

        if (commodityEntity.size() < zeroMQCommodities.size()) {
            Set<String> persistingCommodities = commodityEntity
                    .stream()
                    .map(Commodity::getEddnName)
                    .collect(Collectors.toSet());


            Set<String> newCommodities = new HashSet<>(zeroMQCommodities);

            newCommodities.removeAll(persistingCommodities);
            List<StationCommodity> newStationCommodities = newCommodities
                    .stream()
                    .map(item -> {
                        var mapItem = zeroMQCommodityMap.get(item);
                        CommodityCategory commodityCategory = commodityCategoryRepository
                                .findCommodityCategoryEntityByName(mapItem.getEddnName())
                                .orElse(commodityCategoryRepository.UNKNOWN);
                        Commodity commodity = new Commodity(
                                mapItem.getEddnName(),
                                mapItem.getEddnName(),
                                commodityCategory
                        );
                        return StationCommodity.builder()
                                               .station(station)
                                               .commodity(commodity)
                                               .stock(mapItem.getStock())
                                               .demand(mapItem.getDemand())
                                               .buyPrice(mapItem.getBuyPrice())
                                               .sellPrice(mapItem.getSellPrice())
                                               .build();
                    })
                    .toList();

            stationCommodities.addAll(newStationCommodities);
        }
        station.addCommodities(stationCommodities);
    }
}
