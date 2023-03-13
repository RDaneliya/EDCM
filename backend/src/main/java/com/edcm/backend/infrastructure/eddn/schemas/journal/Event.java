package com.edcm.backend.infrastructure.eddn.schemas.journal;

import com.edcm.backend.core.exceptions.NoSchemaException;

import java.util.Arrays;

public enum Event {
    DOCKED("Docked"),
    FSD_JUMP("FSDJump"),
    SCAN("Scan"),
    LOCATION("Location"),
    SAA_SIGNALS_FOUND("SAASignalsFound"),
    CARRIER_JUMP("CarrierJump"),
    CODEX_ENTRY("CodexEntry");

    private final String eventName;

    Event(String eventName) {
        this.eventName = eventName;
    }

    public static Event valueOfSchema(String eventName) {
        return Arrays.stream(Event.values())
                     .filter(event -> event.eventName.equals(eventName))
                     .findFirst()
                     .orElseThrow(() -> new NoSchemaException("No enum constant " + eventName));
    }
}
