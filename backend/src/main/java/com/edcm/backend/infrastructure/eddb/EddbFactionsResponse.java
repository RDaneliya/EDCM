package com.edcm.backend.infrastructure.eddb;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EddbFactionsResponse {
    private List<EddbFactionItem> factionItems;
}
