package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.mappers.CommodityMapper;
import com.edcm.backend.core.services.category.CategoryTransactionService;
import com.edcm.backend.infrastructure.domain.database.entities.Commodity;
import com.edcm.backend.infrastructure.domain.database.projections.CommodityOverview;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class CommodityTransactionServiceImpl implements CommodityTransactionService {
    private final CommodityRepository repository;
    private final CategoryTransactionService categoryTransactionService;
    private final CommodityMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Commodity createOrFindCommodity(String eddnName) {
        if (repository.existsByEddnName(eddnName)) {
            return repository.getCommodityEntitiesByEddnName(eddnName);
        } else {
            var category = categoryTransactionService.createOrFindCategory("Unknown");
            return repository.saveAndFlush(new Commodity(eddnName, eddnName, category));
        }
    }

    @Override
    public Commodity saveCommodity(Commodity commodity) {
        return repository.save(commodity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Commodity> findAllByEddnName(Collection<String> commodityNames) {
        return repository.findDistinctByEddnNameInIgnoreCase(commodityNames);
    }

    @Override
    public boolean isExistByEddnName(String eddnName) {
        return repository.existsByEddnName(eddnName);
    }

    @Override
    public List<CommodityOverview> getOverview() {
        return repository.getOverview();
    }
}
