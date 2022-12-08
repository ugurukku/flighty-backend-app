package com.tutorials.msflight.repository;

import com.tutorials.msflight.entity.Flight;
import com.tutorials.msflight.enums.FlightStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    default List<Flight> findFlightsByStatusNot(FlightStatus status) {
        return findAllByActiveTrueAndArrivalTimeNotNullAndStatusNot(status);
    }

    Optional<Flight> findByExternalIdAndActiveTrue(UUID flightId);

    List<Flight> findAllByActiveTrue();

    List<Flight> findAllByActiveTrueAndStatus(FlightStatus status);

    List<Flight> findAllByActiveTrueAndArrivalTimeNotNullAndStatusNot(FlightStatus status);
}