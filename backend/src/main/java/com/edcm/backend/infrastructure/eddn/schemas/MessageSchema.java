package com.edcm.backend.infrastructure.eddn.schemas;

import com.edcm.backend.core.exceptions.NoSchemaException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MessageSchema {
    APPROACH_SETTLEMENT("https://eddn.edcd.io/schemas/approachsettlement/1"),
    CODEX_ENTRY("https://eddn.edcd.io/schemas/codexentry/1"),
    COMMODITY("https://eddn.edcd.io/schemas/commodity/3"),
    FC_MATERIALS("https://eddn.edcd.io/schemas/fcmaterials_capi/1"),
    FC_MATERIALS_JOURNAL("https://eddn.edcd.io/schemas/fcmaterials_journal/1"),
    FSS_ALL_BODIES_FOUND("https://eddn.edcd.io/schemas/fssallbodiesfound/1"),
    FSS_DISCOVERY_SCAN("https://eddn.edcd.io/schemas/fssdiscoveryscan/1"),
    FSS_SIGNAL_DISCOVERED("https://eddn.edcd.io/schemas/fsssignaldiscovered/1"),
    FSS_BODY_SIGNALS("https://eddn.edcd.io/schemas/fssbodysignals/1"),
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
            .orElseThrow(() -> new NoSchemaException("No enum constant " + schemaRef));
    }
}
