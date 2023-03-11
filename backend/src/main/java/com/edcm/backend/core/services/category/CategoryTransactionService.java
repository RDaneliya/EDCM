package com.edcm.backend.core.services.category;

import com.edcm.backend.core.shared.data.CommodityCategoryDto;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;

import java.util.Collection;
import java.util.Map;

public interface CategoryTransactionService {
    CommodityCategory UNKNOWN = new CommodityCategory(1L, "Unknown");

    CommodityCategory createOrFind(CommodityCategoryDto category);

    Map<String, CommodityCategory> batchCreateOrFind(Collection<String> categories);
}
