package com.edcm.backend.core.services.category;

import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryTransactionServiceImpl implements CategoryTransactionService {
    private final CommodityCategoryRepository repository;

    @Override
    public CommodityCategory createOrFind(CommodityCategoryDto category) {
        return repository.findCommodityCategoryEntityByName(category.getName())
                         .orElseGet(() -> {
                             var categoryEntity = new CommodityCategory();
                             categoryEntity.setName(category.getName());
                             return repository.save(categoryEntity);
                         });
    }

    @Override
    @Transactional
    public Map<String, CommodityCategory> batchCreateOrFind(Collection<String> categories) {
        return categories
                .stream()
                .map(name -> repository.findCommodityCategoryEntityByName(name)
                                       .orElseGet(() -> repository.save(new CommodityCategory(name)))
                )
                .collect(Collectors.toMap(CommodityCategory::getName, item -> item));

    }
}
