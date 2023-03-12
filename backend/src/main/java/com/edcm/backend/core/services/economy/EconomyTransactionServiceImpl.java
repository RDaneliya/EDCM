package com.edcm.backend.core.services.economy;

import com.edcm.backend.core.mappers.economy.EconomyMapper;
import com.edcm.backend.core.shared.data.EconomyDto;
import com.edcm.backend.infrastructure.domain.database.entities.Economy;
import com.edcm.backend.infrastructure.domain.database.repositories.EconomyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class EconomyTransactionServiceImpl implements EconomyTransactionService {
    private final EconomyRepository economyRepository;
    private final EconomyMapper economyMapper;

    @Override
    public Economy saveEconomy(EconomyDto economyDto) {
        return economyRepository.findByName(economyDto.getName())
                                .orElseGet(() -> economyRepository.save(
                                        new Economy(null, economyDto.getEddnName(), economyDto.getName())
                                ));
    }

    @Override
    public List<Economy> saveAll(Collection<EconomyDto> economyDtos) {
        return economyDtos.stream()
                          .map(this::saveEconomy)
                          .toList();
    }

    @Override
    public List<Economy> findAll(Collection<String> names) {
        return economyRepository.findAllByNameIn(names);
    }
}
