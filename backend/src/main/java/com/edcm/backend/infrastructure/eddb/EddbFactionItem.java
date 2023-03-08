package com.edcm.backend.infrastructure.eddb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EddbFactionItem {
    private Long id;
    private String name;
    @JsonProperty("updated_at")
    private Long updatedAt;
    @JsonSetter("government_id")
    private Long governmentId;
    private String government;
    @JsonProperty("allegiance_id")
    private Long allegianceId;
    private String allegiance;
    @JsonProperty("home_system_id")
    private String homeSystemId;
    @JsonProperty("is_player_faction")
    private Boolean isPlayerFaction;
}
