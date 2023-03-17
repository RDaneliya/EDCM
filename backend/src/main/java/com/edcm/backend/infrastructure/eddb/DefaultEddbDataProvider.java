package com.edcm.backend.infrastructure.eddb;

import com.edcm.backend.core.shared.data.FactionDto;
import com.edcm.backend.core.shared.data.SystemDto;
import com.edcm.backend.core.tools.EddbDataProvider;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DefaultEddbDataProvider implements EddbDataProvider {
    private final EddbOperations eddbOperations;


    @Override
    public List<FactionDto> getFactions() {
       return null;
    }

    @Override
    public List<SystemDto> getSystems() {
        return null;
    }
}
