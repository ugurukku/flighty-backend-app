package com.tutorials.msflight.model;


import static com.tutorials.msflight.util.Constants.DATE_TIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Valid
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightRqModel {

    @NotNull
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime arrivalTime;

    @NotNull
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime departureTime;

    @NotNull
    BigDecimal price;

    @NotNull
    UUID arrivalLocationId;

    @NotNull
    UUID departureLocationId;

    @AssertTrue(message = "Departure time must be before arrival time")
    boolean isTimeValid() {
        return ObjectUtils.allNotNull(arrivalTime, departureTime) && departureTime.isBefore(arrivalTime);
    }

    @AssertFalse(message = "Arrival and departure locations must be different")
    boolean isLocationsValid() {
        return !ObjectUtils.allNotNull(arrivalLocationId, departureLocationId) || arrivalLocationId.equals(departureLocationId);
    }
}
