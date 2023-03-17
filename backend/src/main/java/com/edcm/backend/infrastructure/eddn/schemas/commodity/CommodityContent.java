package com.edcm.backend.infrastructure.eddn.schemas.commodity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommodityContent {
    @JsonProperty("commodities")
    private List<EddnCommodity> commodities;
    @JsonProperty("economies")
    private List<EddnEconomy> economies;
    @JsonProperty("marketId")
    private long marketId;
    @JsonProperty("odyssey")
    private boolean odyssey;
    @JsonProperty("prohibited")
    private List<String> prohibited;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("systemName")
    private String systemName;
    @JsonProperty("timestamp")
    private Date timestamp;
}
