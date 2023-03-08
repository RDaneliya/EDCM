package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CommodityCategoryRepository extends JpaRepository<CommodityCategory, Long> {
    CommodityCategory UNKNOWN = new CommodityCategory(1L, "Unknown");

    @NonNull
    CommodityCategory getCommodityCategoryEntityById(Long id);

    CommodityCategory getCommodityCategoryEntitiesByName(String name);

    Optional<CommodityCategory> findCommodityCategoryEntityByName(String name);

    boolean existsCommodityCategoryEntityByName(String name);


}
