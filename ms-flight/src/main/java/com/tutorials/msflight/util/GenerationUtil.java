package com.tutorials.msflight.util;

import com.tutorials.msflight.entity.Location;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GenerationUtil {

    public static String generateFlightCode(Location departureLocation, Location arrivalLocation) {
        return String.format("%s%s%s",
                String.valueOf(departureLocation.getCity().charAt(0)).toUpperCase(),
                String.valueOf(arrivalLocation.getCity().charAt(0)).toUpperCase(),
                LocalDateTime.now());
    }

}
