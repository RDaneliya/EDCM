package com.edcm.backend.core.tools;

import com.edcm.backend.core.shared.data.FactionDto;


import java.util.List;

public interface EddbDataProvider {
    List<FactionDto> getFactions();
}
