package com.edcm.backend.core.mappers.commodity;

import com.edcm.backend.core.shared.data.StationCommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.StationCommodity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StationCommodityMapperImpl implements StationCommodityMapper {
    private final CommodityMapper commodityMapper;

    @Override
    public StationCommodity toEntity(StationCommodityDto stationCommodityDto) {
        var commodity = commodityMapper.toEntity(stationCommodityDto.getCommodity());
        return new StationCommodity(
                stationCommodityDto.getId(),
                null,
                commodity,
                stationCommodityDto.getStock(),
                stationCommodityDto.getDemand(),
                stationCommodityDto.getBuyPrice(),
                stationCommodityDto.getSellPrice()
        );
    }

    @Override
    public StationCommodityDto toDto(StationCommodity stationCommodity) {
        var commodityDto = commodityMapper.toDto(stationCommodity.getCommodity());
        return StationCommodityDto.builder()
                                  .id(stationCommodity.getId())
                                  .commodity(commodityDto)
                                  .stock(stationCommodity.getStock())
                                  .demand(stationCommodity.getDemand())
                                  .buyPrice(stationCommodity.getBuyPrice())
                                  .sellPrice(stationCommodity.getSellPrice())
                                  .build();
    }
}
