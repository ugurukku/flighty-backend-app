package com.tutorials.msflight.service.impl;

import com.tutorials.msflight.entity.Location;
import com.tutorials.msflight.exception.FlightException;
import com.tutorials.msflight.repository.LocationRepository;
import com.tutorials.msflight.service.LocationService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Log4j2
@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location locationById(UUID id) {
        var location = locationRepository.findByExternalId(id)
                .orElseThrow(() -> new FlightException(String.format("location not found by given id: %s", id)));
        log.info("Location by ID '{}': {}", id, location);

        return location;
    }
}
