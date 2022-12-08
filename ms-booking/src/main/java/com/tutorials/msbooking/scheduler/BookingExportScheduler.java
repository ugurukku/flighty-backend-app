package com.tutorials.msbooking.scheduler;

import com.tutorials.msbooking.entity.Booking;
import com.tutorials.msbooking.enums.FlightStatus;
import com.tutorials.msbooking.exception.BookingException;
import com.tutorials.msbooking.model.FileModel;
import com.tutorials.msbooking.model.SheetModel;
import com.tutorials.msbooking.repository.BookingRepository;
import com.tutorials.msbooking.service.ExcelAdapterService;
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
public class BookingExportScheduler {
    private final ExcelAdapterService excelAdapterService;
    private final BookingRepository bookingRepository;

    @Scheduled(cron = "${scheduler.cron.export-to-file}")
    @SchedulerLock(name = "BookingExportScheduler_exportBookings", lockAtLeastFor = "30s", lockAtMostFor = "3M")
    public void exportBookings() {
        var exportThread = new Thread(() -> {
            log.info("BookingExportScheduler_exportBookings started");

            var bookings = bookingRepository.findAllByFlightStatus(FlightStatus.COMPLETED);
            writeToFile(bookings);
            deactivateBookings(bookings);

            log.info("BookingExportScheduler_exportBookings ended");
        });

        exportThread.setName("exportThread");
        exportThread.start();
    }

    private void writeToFile(List<Booking> bookings) {
        try {
            excelAdapterService.createFile(FileModel.builder().fileName(String.valueOf(LocalDate.now())).build());
            excelAdapterService.addSheet(generateBookingSheet(bookings), String.valueOf(LocalDate.now()));
            log.info("New sheet added successfully");
        } catch (Exception ex) {
            throw new BookingException(
                    String.format("Exception thrown when adding new sheet: %s", ex.getMessage()));
        }
    }

    private void deactivateBookings(List<Booking> bookings) {
        bookings.forEach(booking -> booking.setActive(false));
        bookingRepository.saveAll(bookings);
        log.info("Bookings deactivated: {}", bookings);
    }

    private SheetModel generateBookingSheet(List<Booking> bookings) {
        var data = new TreeMap<String, List<String>>();
            data.put("1. BOOKING ID", new ArrayList<>());
            data.put("2. FLIGHT CODE", new ArrayList<>());
            data.put("3. DEPARTURE LOCATION", new ArrayList<>());
            data.put("4. ARRIVAL LOCATION", new ArrayList<>());
            data.put("5. BOOKED BY", new ArrayList<>());


        bookings.forEach(booking -> {
            data.get("1. BOOKING ID").add(String.valueOf(booking.getExternalId()));
            data.get("2. FLIGHT CODE").add(booking.getFlight().getCode());
            data.get("3. DEPARTURE LOCATION").add(booking.getFlight().getDepartureLocation().getCity());
            data.get("4. ARRIVAL LOCATION").add(booking.getFlight().getArrivalLocation().getCity());
            data.get("5. BOOKED BY").add(booking.getUser().getFullName());
        });

        return SheetModel.builder()
                .sheetName("Completed bookings")
                .data(data)
                .build();
    }
}