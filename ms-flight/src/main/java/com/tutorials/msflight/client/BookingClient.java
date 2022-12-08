package com.tutorials.msflight.client;


import com.tutorials.msflight.model.BookingRsModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.model.ResponseModel;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-booking", url = "${client.url.booking}")
public interface BookingClient {

    @DeleteMapping("/bookings")
    ResponseEntity<ResponseModel<List<BookingRsModel>>> deleteBooking(
            @RequestHeader(name = "Authorization") String auth,
            @RequestParam(name = "flight-id") UUID flightId);

}
