package com.tutorials.msflight.scheduler;

import com.tutorials.msflight.enums.FlightStatus;
import com.tutorials.msflight.repository.FlightRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
@Log4j2
public class FlightStatusScheduler {

    private final FlightRepository flightRepository;

    @Scheduled(cron = "${scheduler.cron.status-update}")
    @SchedulerLock(name = "FlightStatusScheduler_updateFlightStatus", lockAtLeastFor = "30S", lockAtMostFor = "PT3M")
    public void updateFlightStatus() {
        var statusThread = new Thread(() -> {
            log.info("FlightStatusScheduler_updateFlightStatus started");
            var flights = flightRepository.findFlightsByStatusNot(FlightStatus.COMPLETED);

            try {
                if (!CollectionUtils.isEmpty(flights)) {
                    flights.forEach(flight -> {
                        if (flight.getArrivalTime().isBefore(LocalDateTime.now())) {
                            flight.setStatus(FlightStatus.COMPLETED);
                            flightRepository.save(flight);
                            log.info("flight status changed to completed: {}", flight);
                        }
                    });
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
            log.info("FlightStatusScheduler_updateFlightStatus ended");
        });

        statusThread.setName("statusThread");
        statusThread.start();
    }
}