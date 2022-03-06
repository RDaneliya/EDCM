package com.edcm.backend.infrastructure.domain.database.projections;

public interface StationBasicInfo {
    Long getId();

    String getName();

    SystemInfo getSystem();

    interface SystemInfo {
        Long getId();

        String getName();
    }
}
