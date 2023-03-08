package com.edcm.backend.infrastructure.eddn.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZeromqOutfittingPayload {

    @JsonProperty("$schemaRef")
    private String schemaRef;

    @JsonProperty("header")
    private Header header;

    @JsonProperty("message")
    private OutfittingContent message;
}
