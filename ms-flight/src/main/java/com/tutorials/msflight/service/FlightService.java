package com.tutorials.msflight.service;

import com.tutorials.msflight.model.FlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import java.util.List;
import java.util.UUID;

public interface FlightService {
    FlightRsModel createFlight(FlightRqModel request);

    FlightRsModel updateFlight(UUID flightId, FlightRqModel request);

    List<FlightRsModel> getAllFlights();

    FlightRsModel getFlight(UUID id);

    FlightRsModel deleteFlight(String auth, UUID id);
}
