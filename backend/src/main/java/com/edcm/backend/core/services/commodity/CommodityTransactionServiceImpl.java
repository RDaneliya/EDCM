package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.services.category.CategoryTransactionService;
import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.core.shared.data.CommodityDto;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommodityTransactionServiceImpl implements CommodityTransactionService {
    private final CommodityRepository commodityRepository;
    private final CategoryTransactionService categoryTransactionService;

    @Override
    public List<Commodity> saveAll(Collection<CommodityDto> commodityDtos) {
        Set<String> categories = commodityDtos.stream()
                                              .map(infoItem -> {
                                                  return Optional.ofNullable(infoItem.getCategory())
                                                                 .map(CommodityCategoryDto::getName)
                                                                 .orElse(categoryTransactionService.UNKNOWN.getName());
                                              })
                                              .collect(Collectors.toSet());

        Map<String, CommodityCategory> categoryEntityMap = categoryTransactionService.batchCreateOrFind(categories);


        var entities = commodityDtos
                .stream()
                .map(commodity -> commodityRepository
                        .findCommodityEntityByEddnName(commodity.getEddnName())
                        .orElseGet(() -> {
                                    var category = categoryEntityMap.get(commodity.getCategory().getName());
                                    return new Commodity(commodity.getName(), commodity.getEddnName(), category);
                                }
                        ))
                .toList();

        return commodityRepository.saveAll(entities);
    }

    @Override
    public Commodity save(CommodityDto commodityDto) {
        if (commodityRepository.existsByEddnName(commodityDto.getEddnName())) {
            return commodityRepository.findCommodityEntityByEddnName(commodityDto.getEddnName()).get();
        }
        CommodityCategory category = categoryTransactionService.createOrFind(commodityDto.getCategory());
        var commodity = new Commodity(null, commodityDto.getName(), commodityDto.getEddnName(), category);
        return commodityRepository.save(commodity);
    }
}
