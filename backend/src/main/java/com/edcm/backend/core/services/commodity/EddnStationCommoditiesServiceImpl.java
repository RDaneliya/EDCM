package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.services.station.StationTransactionService;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.core.shared.data.StationCommodityDto;
import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.core.shared.data.SystemDto;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnCommodityPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EddnStationCommoditiesServiceImpl implements EddnStationCommoditiesService {

    private final StationTransactionService stationTransactionService;

    @Override
    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Throwable.class)
    public void saveData(EddnCommodityPayload payload) {
        var content = payload.getContent();


        SystemDto systemDto = new SystemDto(null, content.getSystemName(), null, null, null);

        Set<StationCommodityDto> commodityDtos = new LinkedHashSet<>();
        if (content.getCommodities() != null) {
            commodityDtos = content.getCommodities()
                                   .stream()
                                   .map(item -> {
                                       var commodity = new CommodityDto(null, null, item.getEddnName(), null);
                                       return new StationCommodityDto(
                                               null,
                                               commodity,
                                               item.getStock(),
                                               item.getDemand(),
                                               item.getBuyPrice(),
                                               item.getSellPrice()
                                       );
                                   })
                                   .collect(Collectors.toSet());
        }


        Set<EconomyDto> economyDtos = new LinkedHashSet<>();
        if (content.getEconomies() != null) {
            economyDtos = content.getEconomies()
                                 .stream()
                                 .map(item -> new EconomyDto(null, null, item.getName()))
                                 .collect(Collectors.toSet());
        }


        StationDto stationDto = StationDto.builder()
                                          .name(content.getStationName())
                                          .system(systemDto)
                                          .marketId(content.getMarketId())
                                          .commodities(commodityDtos)
                                          .economies(economyDtos)
                                          .build();

        stationTransactionService.saveStation(stationDto);
    }
}
