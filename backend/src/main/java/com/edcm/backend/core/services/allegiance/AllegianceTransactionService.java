package com.edcm.backend.core.services.allegiance;

import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.infrastructure.domain.database.entities.Allegiance;

import java.util.Collection;

public interface AllegianceTransactionService  {

    Allegiance createOrFind(AllegianceDto allegianceDto);

    Collection<Allegiance> createOrFindAll(Collection<AllegianceDto> allegianceDtos);
}
