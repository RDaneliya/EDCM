package com.edcm.backend.infrastructure.domain.database.entities;

import com.edcm.backend.core.shared.data.FactionDto;
import com.edcm.backend.core.shared.data.StateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link FactionState} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactionStateDto implements Serializable {
    private Long id;
    private FactionDto faction;
    private StateDto state;
}
