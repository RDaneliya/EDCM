package com.edcm.backend.infrastructure.eddn.schemas.commodity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EddnEconomy {
    @JsonProperty("name")
    private String name;
    @JsonProperty("proportion")
    private Double proportion;
}
