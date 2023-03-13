package com.edcm.backend.core.services.journal;

import com.edcm.backend.infrastructure.domain.database.entities.Coordinates;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import com.edcm.backend.infrastructure.domain.database.repositories.StationServiceRepository;
import com.edcm.backend.infrastructure.domain.database.entities.System;
import com.edcm.backend.infrastructure.domain.database.repositories.FactionStateRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.SystemRepository;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnEconomy;
import com.edcm.backend.infrastructure.eddn.schemas.journal.EddnJournalDockedPayload;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EddnJournalInformationServiceImpl implements EddnJournalInformationService {
    private final StationRepository stationRepository;
    private final SystemRepository systemRepository;
    private final FactionStateRepository factionStateRepository;
    private final StationServiceRepository stationServiceRepository;

    @Override
    public void saveDockedJournalData(EddnJournalDockedPayload payload) {
        var message = payload.getMessage();

        System system = systemRepository.findByName(message.getSystemName())
                                        .orElse(new System(null, message.getSystemName(), null));


        if (system.getCoordinates() == null) {
            var starPosition = message.getStarPosition();
            Coordinates coordinates = new Coordinates(starPosition.get(0), starPosition.get(1), starPosition.get(3));
            system.setCoordinates(coordinates);
        }

        var economies = message.getStationEconomies().stream()
                               .peek(eddnEconomy -> {
                                   String economyName = eddnEconomy.getName().replace("$economy_", "");
                                   eddnEconomy.setName(economyName);
                               })
                               .toList();
        List<String> economyNames = economies.stream()
                                             .map(EddnEconomy::getName)
                                             .toList();


        Station station = stationRepository
                .findByNameAndSystem_Name(message.getStationName(), message.getSystemName())
                .orElse(new Station(message.getMarketId(), message.getStationName(), system));
        factionStateRepository.delete(station.getFactionState());
        stationServiceRepository.deleteAll(station.getStationServices());

    }
}
