package com.edcm.backend.core.services.allegiance;

import com.edcm.backend.core.mappers.AllegianceMapper;
import com.edcm.backend.core.shared.data.AllegianceDto;
import com.edcm.backend.infrastructure.domain.database.entities.Allegiance;
import com.edcm.backend.infrastructure.domain.database.repositories.AllegianceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AllegianceTransactionServiceImpl implements AllegianceTransactionService {
    private final AllegianceRepository allegianceRepository;
    private final AllegianceMapper mapper;

    @Override
    public Allegiance createOrFind(AllegianceDto allegianceDto) {
        return allegianceRepository.findByName(allegianceDto.getName())
                                   .orElseGet(() -> {
                                       var entity = mapper.toEntity(allegianceDto);
                                       return allegianceRepository.save(entity);
                                   });
    }

    @Override
    public Collection<Allegiance> createOrFindAll(Collection<AllegianceDto> allegianceDtos) {
        return allegianceDtos.stream()
                             .map(mapper::toEntity)
                             .map(item -> {
                                 if (!allegianceRepository.existsByName(item.getName())) {
                                     return allegianceRepository.save(item);
                                 }
                                 return item;
                             })
                             .toList();
    }
}
