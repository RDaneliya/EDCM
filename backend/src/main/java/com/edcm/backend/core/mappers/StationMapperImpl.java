package com.edcm.backend.core.mappers;

import com.edcm.backend.core.mappers.commodity.StationCommodityMapper;
import com.edcm.backend.core.mappers.economy.EconomyMapper;
import com.edcm.backend.core.shared.data.StationDto;
import com.edcm.backend.infrastructure.domain.database.entities.Station;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StationMapperImpl implements StationMapper {
    private final StationCommodityMapper stationCommodityMapper;
    private final SystemMapper systemMapper;
    private final StationTypeMapper stationTypeMapper;
    private final EconomyMapper economyMapper;

    @Override
    public StationDto toDto(@NotNull Station entity) {
        var stationCommodityDtos = entity.getCommodities().stream()
                                         .map(stationCommodityMapper::toDto)
                                         .toList();


        var economyDtos = entity.getEconomies().stream()
                                .map(economyMapper::toDto)
                                .toList();

        var system = systemMapper.toDto(entity.getSystem());

        var stationType = stationTypeMapper.toDto(entity.getStationType());

        return StationDto.builder()
                         .id(entity.getId())
                         .name(entity.getName())
                         .system(system)
                         .commodities(stationCommodityDtos)
                         .stationTypeDto(stationType)
                         .economies(economyDtos)
                         .createdAt(entity.getCreatedAt())
                         .updatedAt(entity.getUpdatedAt())
                         .build();
    }

    @Override
    public Station toEntity(@NotNull StationDto dto) {
        var stationCommodities = dto.getCommodities().stream()
                                    .map(stationCommodityMapper::toEntity)
                                    .toList();

        var economies = dto.getEconomies().stream()
                           .map(economyMapper::toEntity)
                           .collect(Collectors.toSet());

        var system = systemMapper.toEntity(dto.getSystem());

        var stationType = stationTypeMapper.toEntity(dto.getStationTypeDto());

        return new Station(
                dto.getId(),
                dto.getName(),
                system,
                stationCommodities,
                economies,
                stationType,
                null,
                null,
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
    }
}
