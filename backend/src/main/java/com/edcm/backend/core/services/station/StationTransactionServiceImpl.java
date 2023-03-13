package com.edcm.backend.core.services.station;

import com.edcm.backend.core.mappers.StationMapper;
import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.repositories.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StationTransactionServiceImpl implements StationTransactionService {
    private final StationRepository stationRepository;
    private final StationMapper mapper;


    @Override
    public StationDto createOrFindCarrier(String code) {
        return stationRepository.findByName(code)
                                .map(mapper::toDto)
                                .orElseGet(() -> StationDto.builder().name(code).build());
    }

    @Override
    @Modifying
    public StationDto saveStation(StationDto station) {
        var stationEntity = mapper.toEntity(station);
        stationEntity = stationRepository.saveAndFlush(stationEntity);
        return mapper.toDto(stationEntity);
    }
}
