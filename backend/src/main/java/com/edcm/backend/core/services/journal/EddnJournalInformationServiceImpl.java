package com.edcm.backend.core.services.journal;

import com.edcm.backend.core.services.station.StationTransactionService;
import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.core.shared.data.GovernmentDto;
import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.core.shared.data.StationTypeDto;
import com.edcm.backend.core.shared.data.SystemDto;
import com.edcm.backend.infrastructure.eddn.schemas.journal.EddnJournalDockedMessage;
import com.edcm.backend.infrastructure.eddn.schemas.journal.EddnJournalDockedPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class EddnJournalInformationServiceImpl implements EddnJournalInformationService {

    private final StationTransactionService stationTransactionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveDockedJournalData(EddnJournalDockedPayload payload) {
        var message = payload.getMessage();

        SystemDto systemDto = getSystemDto(message);
        StationTypeDto stationTypeDto = null;
        if(message.getStationType() != null) {
            stationTypeDto = getStationTypeDto(message);
        }
        Set<EconomyDto> economyDtos = message
                .getStationEconomies()
                .stream()
                .map(economy -> new EconomyDto(null, economy.getName(), null))
                .collect(Collectors.toSet());


        var updatedStation = StationDto.builder()
                                       .marketId(message.getMarketId())
                                       .name(message.getStationName())
                                       .system(systemDto)
                                       .stationTypeDto(stationTypeDto)
                                       .economies(economyDtos)
                                       .factionState(null)
                                       .build();
        stationTransactionService.saveStation(updatedStation);
    }

    @NotNull
    private static StationTypeDto getStationTypeDto(EddnJournalDockedMessage message) {
        var landingPads = message.getLandingPads();
        StationTypeDto.LandingPadsDto landingPadsDto = new StationTypeDto.LandingPadsDto(
                landingPads.getLarge(),
                landingPads.getMedium(),
                landingPads.getSmall()
        );
        StationTypeDto stationTypeDto = new StationTypeDto(null, message.getStationType(), landingPadsDto);
        return stationTypeDto;
    }

    @NotNull
    private static SystemDto getSystemDto(EddnJournalDockedMessage message) {
        var position = message.getStarPosition();
        var allegianceDto = new AllegianceDto(null, message.getStationAllegiance(), null, null);
        var economyDto = new EconomyDto(null, message.getSystemEconomy(), null);
        var coordinates = new SystemDto.CoordinatesDto(position.get(0), position.get(1), position.get(2));
        return new SystemDto(null, message.getSystemName(), coordinates, allegianceDto, economyDto);
    }
}
