package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {


    Optional<Commodity> findCommodityEntityByEddnName(String eddnName);

    List<Commodity> findByEddnNameInIgnoreCase(@NonNull Collection<String> eddnNames);

    boolean existsByName(String name);

    boolean existsByEddnName(String eddnName);
}
