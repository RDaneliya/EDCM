package com.edcm.backend.core.tools.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StationUtils {
    public static boolean isCarrier(String stationName){
        return stationName.matches("([A-Z0-9]){3}-([A-Z0-9]){3}");
    }
}
