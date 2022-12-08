package com.tutorials.msflight.scheduler;

import com.tutorials.msflight.entity.Flight;
import com.tutorials.msflight.enums.FlightStatus;
import com.tutorials.msflight.exception.FlightException;
import com.tutorials.msflight.model.FileModel;
import com.tutorials.msflight.model.SheetModel;
import com.tutorials.msflight.repository.FlightRepository;
import com.tutorials.msflight.service.ExcelAdapterService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class FlightExportScheduler {
    private final FlightRepository flightRepository;
    private final ExcelAdapterService excelAdapterService;

    @Scheduled(cron = "${scheduler.cron.export-to-file}")
    @SchedulerLock(name = "FlightExportScheduler_exportAndDeactivateFlights", lockAtLeastFor = "30s", lockAtMostFor = "50s")
    public void exportAndDeactivateFlights() {
        var exportThread = new Thread(() -> {
            log.info("FlightExportScheduler_exportToFile started");
            var flights = flightRepository.findAllByActiveTrueAndStatus(FlightStatus.COMPLETED);

            writeToFile(flights);
            deactivateFlights(flights);

            log.info("FlightExportScheduler_exportAndDeactivateFlights ended");
        });

        exportThread.setName("exportThread");
        exportThread.start();
    }

    private void writeToFile(List<Flight> flights) {
        try {
            excelAdapterService.createFile(FileModel.builder().fileName(String.valueOf(LocalDate.now())).build());
            excelAdapterService.addSheet(generateFlightSheet(flights), String.valueOf(LocalDate.now()));

            log.info("New sheet added successfully");
        } catch (Exception ex) {
            throw new FlightException(
                    String.format("Exception thrown when adding new sheet: %s", ex.getMessage()));
        }
    }

    private void deactivateFlights(List<Flight> flights) {
        flights.forEach(flight -> flight.setActive(false));
        flightRepository.saveAll(flights);
        log.info("Flights deactivated: {}", flights);
    }

    private SheetModel generateFlightSheet(List<Flight> flights) {
        var data = new TreeMap<String, List<String>>(){{
            put("1. FLIGHT ID", new ArrayList<>());
            put("2. CODE", new ArrayList<>());
            put("3. DEPARTURE LOCATION", new ArrayList<>());
            put("4. ARRIVAL LOCATION", new ArrayList<>());
        }};

        flights.forEach(flight -> {
            data.get("1. FLIGHT ID").add(String.valueOf(flight.getExternalId()));
            data.get("2. CODE").add(flight.getCode());
            data.get("3. DEPARTURE LOCATION").add(flight.getDepartureLocation().getCity());
            data.get("4. ARRIVAL LOCATION").add(flight.getArrivalLocation().getCity());
        });

        return SheetModel.builder()
                .sheetName("Completed flights")
                .data(data)
                .build();
    }
}