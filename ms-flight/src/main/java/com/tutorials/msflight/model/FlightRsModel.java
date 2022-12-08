package com.tutorials.msflight.model;

import static com.tutorials.msflight.util.Constants.DATE_TIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightRsModel {
    UUID id;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime arrivalTime;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    LocalDateTime departureTime;

    BigDecimal price;
    LocationModel arrivalLocation;
    LocationModel departureLocation;
}
