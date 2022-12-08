package com.tutorials.msflight.controller;

import static com.tutorials.msflight.util.Constants.FLIGHTS_URL;
import static com.tutorials.msflight.util.Constants.PATH_ID;
import static com.tutorials.msflight.util.Constants.REQUEST_LOG_FORMAT;
import static com.tutorials.msflight.util.Constants.RESPONSE_LOG_FORMAT;

import com.tutorials.msflight.model.FlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.service.FlightService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(FLIGHTS_URL)
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightRsModel> createFlight(@Valid @RequestBody FlightRqModel request) {
        log.info(REQUEST_LOG_FORMAT, FLIGHTS_URL, request);
        var response = flightService.createFlight(request);
        log.info(RESPONSE_LOG_FORMAT, FLIGHTS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<FlightRsModel> updateFlight(
            @PathVariable(name = "id") UUID id,
            @Valid @RequestBody FlightRqModel request) {
        log.info(REQUEST_LOG_FORMAT, FLIGHTS_URL + PATH_ID, request);
        var response = flightService.updateFlight(id, request);
        log.info(RESPONSE_LOG_FORMAT, FLIGHTS_URL + PATH_ID, response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<FlightRsModel>> getAllFlights() {
        log.info(REQUEST_LOG_FORMAT, FLIGHTS_URL, null);
        var response = flightService.getAllFlights();
        log.info(RESPONSE_LOG_FORMAT, FLIGHTS_URL, response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<FlightRsModel> getFlight(@PathVariable(name = "id") UUID id) {
        log.info(REQUEST_LOG_FORMAT, FLIGHTS_URL + PATH_ID, null);
        var response = flightService.getFlight(id);
        log.info(RESPONSE_LOG_FORMAT, FLIGHTS_URL + PATH_ID, response);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<FlightRsModel> deleteFlight(@RequestHeader(name = "Authorization") String auth,
                                                      @PathVariable(name = "id") UUID id) {
        log.info(REQUEST_LOG_FORMAT, FLIGHTS_URL + PATH_ID, null);
        var response = flightService.deleteFlight(auth, id);
        log.info(RESPONSE_LOG_FORMAT, FLIGHTS_URL + PATH_ID, response);
        return ResponseEntity.ok().body(response);
    }

}
