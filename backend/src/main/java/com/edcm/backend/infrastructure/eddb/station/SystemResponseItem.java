package com.edcm.backend.infrastructure.eddb.station;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemResponseItem {
    private Long id;
    @JsonProperty("edsm_id")
    private Long edsmId;
    private String name;
    private Double x;
    private Double y;
    private Double z;
    private Long population;
    private Boolean isPopulated;
    @JsonProperty("government_id")
    private Long governmentId;
    private String government;
    @JsonProperty("allegiance_id")
    private Long allegianceId;
    private String allegiance;
    private List<State> states;
    @JsonProperty("security_id")
    private Long securityId;
    private String security;
    @JsonProperty("primary_economy_id")
    private Long primaryEconomyId;
    @JsonProperty("primary_economy")
    private String primaryEconomy;
    private String power;
    @JsonProperty("power_state")
    private String powerState;
    @JsonProperty("power_state_id")
    private Long powerStateId;
    @JsonProperty("needs_permit")
    private Boolean needsPermit;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("minor_factions_updated_at")
    private LocalDateTime minorFactionUpdatedAt;
    @JsonProperty("simbad_ref")
    private String simbadReference;
    @JsonProperty("controlling_minor_faction_id")
    private Long controllingMinorFactionId;
    @JsonProperty("controlling_minor_faction")
    private String controllingMinorFaction;
    @JsonProperty("reserve_type_id")
    private String reserveTypeId;
    @JsonProperty("reserve_type")
    private String reserveType;
    @JsonProperty("minor_faction_presences")
    private List<MinorFaction> minorFactionPresences;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class State {
        private String id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MinorFaction{
        @JsonProperty("happiness_id")
        private Long happinessId;
        @JsonProperty("minor_faction_id")
        private Long minorFactionId;
        private Double influence;
        @JsonProperty("active_states")
        private List<State> activeStates;
        @JsonProperty("pending_states")
        private List<State> pendingStates;
        @JsonProperty("recovering_states")
        private List<State> recoveringStates;
    }
}
