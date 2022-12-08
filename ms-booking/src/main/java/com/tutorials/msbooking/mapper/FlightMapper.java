package com.tutorials.msbooking.mapper;

import static com.tutorials.msbooking.mapper.LocationMapper.LOCATION_MAPPER_INSTANCE;
import static com.tutorials.msbooking.mapper.UserMapper.USER_MAPPER_INSTANCE;

import com.tutorials.msbooking.entity.Booking;
import com.tutorials.msbooking.entity.Flight;
import com.tutorials.msbooking.model.BookingRsModel;
import com.tutorials.msbooking.model.FlightModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FlightMapper {

    public static final FlightMapper FLIGHT_MAPPER_INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(source = "externalId", target = "id")
    @Mapping(target = "arrivalLocation", ignore = true)
    @Mapping(target = "departureLocation", ignore = true)
    public abstract FlightModel mapEntityToResponse(Flight flight);

    @AfterMapping
    void mapAdditionalFields(@MappingTarget FlightModel.FlightModelBuilder response, Flight flight) {
        response.arrivalLocation(LOCATION_MAPPER_INSTANCE.mapEntityToResponse(flight.getArrivalLocation()));
        response.departureLocation(LOCATION_MAPPER_INSTANCE.mapEntityToResponse(flight.getDepartureLocation()));
    }

}
