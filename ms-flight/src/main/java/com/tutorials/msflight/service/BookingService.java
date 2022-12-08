package com.tutorials.msflight.service;

import com.tutorials.msflight.model.BookingRsModel;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    List<BookingRsModel> deleteBooking(String auth, UUID flightId);
}
