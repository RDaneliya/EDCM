package com.edcm.backend.core.zeromq;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MessageSchema {
    APPROACH_SETTLEMENT("https://eddn.edcd.io/schemas/approachsettlement/1"),
    CODEX_ENTRY("https://eddn.edcd.io/schemas/codexentry/1"),
    COMMODITY("https://eddn.edcd.io/schemas/commodity/3"),
    FSS_ALL_BODIES_FOUND("https://eddn.edcd.io/schemas/fssallbodiesfound/1"),
    FSS_DISCOVERY_SCAN("https://eddn.edcd.io/schemas/fssdiscoveryscan/1"),
    JOURNAL("https://eddn.edcd.io/schemas/journal/1"),
    NAVBEACON("https://eddn.edcd.io/schemas/navbeaconscan/1"),
    NAVROUTE("https://eddn.edcd.io/schemas/navroute/1"),
    OUTFITTING("https://eddn.edcd.io/schemas/outfitting/2"),
    SCAN_BARY_CENTRE("https://eddn.edcd.io/schemas/scanbarycentre/1"),
    SHIPYARD("https://eddn.edcd.io/schemas/shipyard/2");

    private final String schemaRef;

    MessageSchema(String schemaRef) {
        this.schemaRef = schemaRef;
    }

    public static MessageSchema valueOfSchema(String schemaRef) {
        return Arrays.stream(MessageSchema.values())
            .filter(messageSchema -> messageSchema.getSchemaRef().equals(schemaRef))
            .findFirst()
            .orElseThrow(() ->
                new IllegalStateException("No enum constant com.edcm.backend.core.zeromq.MessageSchema." + schemaRef));
    }
}
