package com.tutorials.msflight.service.impl;

import static com.tutorials.msflight.mapper.FlightMapper.FLIGHT_MAPPER_INSTANCE;
import static com.tutorials.msflight.mapper.LocationMapper.LOCATION_MAPPER_INSTANCE;

import com.tutorials.msflight.entity.Flight;
import com.tutorials.msflight.exception.FlightException;
import com.tutorials.msflight.model.FlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.repository.FlightRepository;
import com.tutorials.msflight.service.BookingService;
import com.tutorials.msflight.service.FlightService;
import com.tutorials.msflight.service.LocationService;
import com.tutorials.msflight.util.GenerationUtil;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Log4j2
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final LocationService locationService;
    private final BookingService bookingService;

    @Override
    public FlightRsModel createFlight(FlightRqModel request) {
        var departureLocation = locationService.locationById(request.getDepartureLocationId());
        var arrivalLocation = locationService.locationById(request.getArrivalLocationId());

        var flight = FLIGHT_MAPPER_INSTANCE.mapRequestToEntity(request);
        flight.setArrivalLocation(arrivalLocation);
        flight.setDepartureLocation(departureLocation);
        flight.setCode(GenerationUtil.generateFlightCode(departureLocation, arrivalLocation));
        flightRepository.save(flight);

        log.info("Flight saved: {}", flight);
        return getFlightResponse(flight);
    }

    @Override
    public FlightRsModel updateFlight(UUID id, FlightRqModel request) {
        var flight = flightById(id);

        updateFlightValues(request, flight);
        flightRepository.save(flight);
        log.info("Flight updated: {}", flight);

        return getFlightResponse(flight);
    }

    @Override
    public List<FlightRsModel> getAllFlights() {
        var flights = flightRepository.findAllByActiveTrue();

        return flights.stream()
                .map(FLIGHT_MAPPER_INSTANCE::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FlightRsModel getFlight(UUID id) {
        return FLIGHT_MAPPER_INSTANCE.mapEntityToResponse(flightById(id));
    }

    @Override
    public FlightRsModel deleteFlight(String auth, UUID id) {
        var flight = flightById(id);
        var deleteBookingsResponse = bookingService.deleteBooking(auth, flight.getExternalId());
        log.info("Bookings deleted: {}", deleteBookingsResponse);

        flight.setActive(false);
        flightRepository.save(flight);
        log.info("Flight active is set to false: {}", flight);

        return FLIGHT_MAPPER_INSTANCE.mapEntityToResponse(flight);
    }

    private void updateFlightValues(FlightRqModel request, Flight flight) {
        var departureLocation = locationService.locationById(request.getDepartureLocationId());
        var arrivalLocation = locationService.locationById(request.getArrivalLocationId());
        var code = GenerationUtil.generateFlightCode(departureLocation, arrivalLocation);

        flight.setArrivalLocation(arrivalLocation);
        flight.setDepartureLocation(departureLocation);
        flight.setCode(code);
        flight.setPrice(request.getPrice());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setDepartureTime(request.getDepartureTime());
    }


    private Flight flightById(UUID id) {
        var flight = flightRepository.findByExternalIdAndActiveTrue(id)
                .orElseThrow(() -> new FlightException(String.format("flight not found by given id: %s", id)));
        log.info("Flight by ID '{}': {}", id, flight);


        return flight;
    }


    private FlightRsModel getFlightResponse(Flight flight) {
        var response = FLIGHT_MAPPER_INSTANCE.mapEntityToResponse(flight);
        response.setDepartureLocation(LOCATION_MAPPER_INSTANCE.mapEntityToResponse(flight.getDepartureLocation()));
        response.setArrivalLocation(LOCATION_MAPPER_INSTANCE.mapEntityToResponse(flight.getArrivalLocation()));
        return response;
    }

}
