package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.edcm.backend.infrastructure.domain.database.entities.Government} entity
 */
@Data
public class GovernmentDto implements Serializable {
    private final Long id;
    private final String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GovernmentDto that)) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
