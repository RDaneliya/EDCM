package com.edcm.backend.core.services.faction;

import com.edcm.backend.core.mappers.AllegianceMapper;
import com.edcm.backend.core.mappers.FactionMapper;
import com.edcm.backend.core.mappers.GovernmentMapper;
import com.edcm.backend.core.shared.data.FactionDto;
import com.edcm.backend.infrastructure.domain.database.entities.Allegiance;
import com.edcm.backend.infrastructure.domain.database.entities.Government;
import com.edcm.backend.infrastructure.domain.database.repositories.AllegianceRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.FactionRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.GovernmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FactionsServiceImpl implements FactionsService {
    private final FactionRepository factionRepository;
    private final FactionMapper factionMapper;
    private final GovernmentMapper governmentMapper;
    private final AllegianceMapper allegianceMapper;
    private final GovernmentRepository governmentRepository;
    private final AllegianceRepository allegianceRepository;

    @Override
    public FactionDto saveFaction(FactionDto factionDto) {
        var entity = factionMapper.toEntity(factionDto);
        entity = factionRepository.save(entity);
        return factionMapper.toDto(entity);
    }

    @Override
    public void saveFactions(List<FactionDto> factionDtos) {
        var factions = factionDtos.stream()
                                  .map(factionMapper::toEntity)
                                  .toList();

        var governments = factionDtos.stream()
                                     .map(FactionDto::getGovernment)
                                     .collect(Collectors.toSet())
                                     .stream()
                                     .map(governmentMapper::toEntity)
                                     .collect(Collectors.toSet());

        Map<String, Government> governmentMap = governmentRepository
                .saveAll(governments)
                .stream()
                .collect(Collectors.toMap(Government::getName, item -> item));

        var allegiances = factionDtos.stream()
                                     .map(FactionDto::getAllegiance)
                                     .collect(Collectors.toSet())
                                     .stream()
                                     .map(allegianceMapper::toEntity)
                                     .collect(Collectors.toSet());

        Map<String, Allegiance> allegianceMap = allegianceRepository
                .saveAll(allegiances)
                .stream()
                .collect(Collectors.toMap(Allegiance::getName, allegiance -> allegiance));

        factions = factions.stream()
                           .peek(faction -> {
                               var allegiance = allegianceMap.get(faction.getAllegiance().getName());
                               faction.setAllegiance(allegiance);
                               var government = governmentMap.get(faction.getGovernment().getName());
                               faction.setGovernment(government);
                           })
                           .collect(Collectors.toList());


        factionRepository.saveAll(factions);
    }
}
