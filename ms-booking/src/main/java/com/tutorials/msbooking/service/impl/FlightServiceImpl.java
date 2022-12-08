package com.tutorials.msbooking.service.impl;

import com.tutorials.msbooking.entity.Flight;
import com.tutorials.msbooking.exception.BookingException;
import com.tutorials.msbooking.repository.FlightRepository;
import com.tutorials.msbooking.service.FlightService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Flight flightById(UUID id) {
        var flight = flightRepository.findByExternalIdAndActiveTrue(id)
                .orElseThrow(() -> new BookingException("Flight not found by given ID"));

        log.info("Flight by ID '{}': {}", id, flight);
        return flight;
    }
}
