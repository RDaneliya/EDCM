package com.edcm.backend.infrastructure.eddn.schemas.commodity;

import com.edcm.backend.infrastructure.eddn.schemas.EddnPayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EddnCommodityPayload extends EddnPayload {

    @JsonProperty("message")
    private CommodityContent content;
}
