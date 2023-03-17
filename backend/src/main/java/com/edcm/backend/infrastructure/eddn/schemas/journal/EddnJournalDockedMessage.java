package com.edcm.backend.infrastructure.eddn.schemas.journal;

import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnEconomy;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EddnJournalDockedMessage {

    @JsonProperty("DistFromStarLS")
    private Long distantToStarLs;

    @JsonProperty("LandingPads")
    private LandingPads landingPads;

    @JsonProperty("MarketID")
    private Long marketId;

    @JsonProperty("Multicrew")
    private Boolean multicrew;

    @JsonProperty("StarPos")
    private List<Double> starPosition;

    @JsonProperty("StarSystem")
    private String systemName;

    @JsonProperty("StationName")
    private String stationName;

    @JsonProperty("StationAllegiance")
    private String stationAllegiance;

    @JsonProperty("SystemEconomy")
    private String systemEconomy;

    @JsonProperty("StationEconomies")
    private List<EddnEconomy> stationEconomies;

    @JsonProperty("StationFaction")
    private StationFaction stationFaction;

    @JsonProperty("StationGovernment")
    private String stationGovernment;

    @JsonProperty("StationServices")
    private List<String> stationServices;

    @JsonProperty("StationType")
    private String stationType;

    @JsonProperty("event")
    private String event;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LandingPads {

        @JsonProperty("Large")
        private Long large;

        @JsonProperty("Medium")
        private Long medium;

        @JsonProperty("Small")
        private Long small;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StationFaction {
        @JsonProperty("FactionState")
        private String factionState;
        @JsonProperty("Name")
        private String name;
    }
}
