package com.edcm.backend.infrastructure.eddn.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class EddnPayload {
    @JsonProperty("$schemaRef")
    protected String schemaRef;

    @JsonProperty("header")
    protected Header header;
}
