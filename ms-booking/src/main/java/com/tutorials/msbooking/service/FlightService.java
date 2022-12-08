package com.tutorials.msbooking.service;

import com.tutorials.msbooking.entity.Flight;
import java.util.UUID;

public interface FlightService {
    Flight flightById(UUID id);
}
