package com.edcm.backend.core.shared.data;

import java.io.Serializable;

public record StationTypeDto(Long id, String type, StationTypeDto.LandingPadsDto landingPads) implements Serializable
{
    public record LandingPadsDto(long large, long medium, long small) implements Serializable {
    }
}
