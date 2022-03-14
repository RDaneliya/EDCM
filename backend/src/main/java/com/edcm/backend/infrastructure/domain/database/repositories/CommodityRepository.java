package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.projections.CommodityOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

    Commodity getCommodityEntityByName(String name);

    Commodity getCommodityEntitiesById(Long id);

    Commodity getCommodityEntitiesByEddnName(String eddnName);

    Optional<Commodity> findCommodityEntityByEddnName(String eddnName);

    List<Commodity> getCommodityEntitiesByCategory(CommodityCategory category);

    List<Commodity> findDistinctByEddnNameInIgnoreCase(@NonNull Collection<String> eddnNames);

    @Query(value = """
        SELECT
            min(stationCommodity.buyPrice) as minBuyPrice,
            max (stationCommodity.sellPrice) as maxSellPrice,
            commodity.id as commodityId,
            commodity.name as commodityName,
            commodity.category.name as commodityCategory
        FROM StationCommodity stationCommodity
        JOIN Commodity commodity on stationCommodity.commodity = commodity
        JOIN Station station on stationCommodity.station = station
        JOIN station.economies economy
        WHERE economy.economyName.name <> 'Carrier' and stationCommodity.buyPrice > 0
        GROUP BY commodityName, commodityId, commodityCategory
        """)
    List<CommodityOverview> getOverview();

    boolean existsByName(String name);

    boolean existsByEddnName(String eddnName);
}
