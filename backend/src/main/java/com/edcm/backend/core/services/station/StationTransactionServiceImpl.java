package com.edcm.backend.core.services.station;

import com.edcm.backend.core.mappers.StationMapper;
import com.edcm.backend.core.mappers.SystemMapper;
import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.core.services.economy.EconomyTransactionService;
import com.edcm.backend.core.services.system.SystemTransactionService;
import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import com.edcm.backend.infrastructure.domain.database.entities.LandingPads;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import com.edcm.backend.infrastructure.domain.database.entities.StationType;
import com.edcm.backend.infrastructure.domain.database.entities.StationTypeRepository;
import com.edcm.backend.infrastructure.domain.database.entities.System;
import com.edcm.backend.infrastructure.domain.database.repositories.AllegianceRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationCommodityRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class StationTransactionServiceImpl implements StationTransactionService {
    private final AllegianceRepository allegianceRepository;
    private final StationCommodityRepository stationCommodityRepository;
    private final StationTypeRepository stationTypeRepository;
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final SystemMapper systemMapper;
    private final SystemTransactionService systemTransactionService;
    private final CommodityTransactionService commodityTransactionService;
    private final EconomyTransactionService economyTransactionService;


    @Override
    public StationDto createOrFindStation(Long code) {
        return stationRepository.findByMarketId(code)
                                .map(stationMapper::toDto)
                                .orElseGet(() -> StationDto.builder().marketId(code).build());
    }

    @Override
    public StationDto saveStation(StationDto stationDto) {
        System system = initializeSystem(stationDto);

        Optional<Station> stationOptional = stationRepository.findByMarketId(stationDto.getMarketId());

        Station stationEntity = unboxStation(stationOptional, system, stationDto);

        if (!CollectionUtils.isEmpty(stationDto.getCommodities())) {
            initCommodities(stationDto, stationEntity);
        }

        if (!CollectionUtils.isEmpty(stationDto.getEconomies())) {
            initEconomies(stationDto, stationEntity);
        }

        if (stationDto.getStationTypeDto() != null) {
            var type = stationTypeRepository
                    .findByType(stationDto.getStationTypeDto().type())
                    .orElseGet(() -> {
                        var padsDto = stationDto.getStationTypeDto().landingPads();
                        var pads = new LandingPads(padsDto.large(), padsDto.medium(), padsDto.small());
                        return new StationType(null, stationDto.getStationTypeDto().type(), pads);
                    });
            stationEntity.setStationType(type);
        }


        try {
            var savedStation = stationRepository.save(stationEntity);
            log.info("Saved station '{}' in '{}'", savedStation.getName(), savedStation.getSystem().getName());
            return stationMapper.toDto(savedStation);
        } catch (DataAccessException e) {
            log.error("Error saving entity: {}", stationEntity, e);
        }
        return null;
    }

    private void initEconomies(StationDto stationDto, Station station) {
        List<String> economyNames = stationDto
                .getEconomies()
                .stream()
                .map(EconomyDto::getEddnName)
                .toList();
        Set<Economy> economies = economyTransactionService.findAll(economyNames);
        station.setEconomies(new LinkedHashSet<>(economies));
    }

    private void initCommodities(StationDto stationDto, Station station) {
        List<String> commodityNames = stationDto
                .getCommodities()
                .stream()
                .map(stationCommodityDto -> stationCommodityDto.getCommodity().getEddnName())
                .toList();

        Map<String, Commodity> commodityMap = commodityTransactionService.findAll(commodityNames);


        Set<StationCommodity> stationCommodities = stationDto
                .getCommodities()
                .stream()
                .map(item -> {
                    var commodity = commodityMap.get(item.getCommodity().getEddnName());
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
                .collect(Collectors.toSet());

        stationCommodityRepository.deleteByStation(station);
        station.setCommodities(stationCommodities);
    }

    private Station unboxStation(Optional<Station> optionalStation, System system, StationDto stationDto) {
        return optionalStation
                .map(stationEntity -> {
                    stationEntity.setName(stationDto.getName());
                    stationEntity.setSystem(system);
                    return stationEntity;
                })
                .orElseGet(() -> {
                            var station = new Station(stationDto.getMarketId(), stationDto.getName(), system);
                            return stationRepository.save(station);
                        }
                );
    }

    private System initializeSystem(StationDto stationDto) {
        System system = systemTransactionService.createOrFindSystem(stationDto.getSystem().getName());

        if (stationDto.getSystem().getAllegiance() != null) {
            var allegiance = allegianceRepository.findByEddnId(stationDto.getSystem().getAllegiance().getEddnId());
            system.setAllegiance(allegiance);
        }

        return system;
    }
}
