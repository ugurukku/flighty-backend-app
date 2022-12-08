package com.tutorials.msbooking.repository;

import com.tutorials.msbooking.entity.Flight;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByExternalIdAndActiveTrue(UUID id);
}
