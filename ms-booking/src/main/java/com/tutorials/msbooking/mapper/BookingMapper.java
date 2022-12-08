package com.tutorials.msbooking.mapper;

import static com.tutorials.msbooking.mapper.FlightMapper.FLIGHT_MAPPER_INSTANCE;
import static com.tutorials.msbooking.mapper.UserMapper.USER_MAPPER_INSTANCE;

import com.tutorials.msbooking.entity.Booking;
import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class BookingMapper {

    public static final BookingMapper BOOKING_MAPPER_INSTANCE = Mappers.getMapper(BookingMapper.class);

    public abstract Booking mapRequestToEntity(BookingRqModel request);

    @Mapping(source = "externalId", target = "id")
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "user", ignore = true)
    public abstract BookingRsModel mapEntityToResponse(Booking booking);

    @AfterMapping
    void mapAdditionalFields(@MappingTarget BookingRsModel.BookingRsModelBuilder response, Booking booking) {
        response.flight(FLIGHT_MAPPER_INSTANCE.mapEntityToResponse(booking.getFlight()));
        response.user(USER_MAPPER_INSTANCE.mapEntityToResponse(booking.getUser()));
    }

}
