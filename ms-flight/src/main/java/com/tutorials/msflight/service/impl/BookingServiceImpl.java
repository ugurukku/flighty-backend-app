package com.tutorials.msflight.service.impl;

import com.tutorials.msflight.client.BookingClient;
import com.tutorials.msflight.model.BookingRsModel;
import com.tutorials.msflight.service.BookingService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Log4j2
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingClient bookingClient;

    @Override
    public List<BookingRsModel> deleteBooking(String auth, UUID flightId) {
        var response = bookingClient.deleteBooking(auth, flightId);
        log.info("Delete booking response: {}", response);
        return Objects.requireNonNull(response.getBody()).getData();
    }
}
