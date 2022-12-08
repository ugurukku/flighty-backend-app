package com.tutorials.msflight.mapper;

import static com.tutorials.msflight.mapper.LocationMapper.LOCATION_MAPPER_INSTANCE;

import com.tutorials.msflight.entity.Flight;
import com.tutorials.msflight.model.FlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FlightMapper {

    public static final FlightMapper FLIGHT_MAPPER_INSTANCE = Mappers.getMapper(FlightMapper.class);

    public abstract Flight mapRequestToEntity(FlightRqModel request);

    @Mapping(target = "id", source = "externalId")
    @Mapping(target = "arrivalLocation", ignore = true)
    @Mapping(target = "departureLocation", ignore = true)
    public abstract FlightRsModel mapEntityToResponse(Flight flight);

    @AfterMapping
    void mapLocations(@MappingTarget FlightRsModel.FlightRsModelBuilder response, Flight flight) {
        response.departureLocation(LOCATION_MAPPER_INSTANCE.mapEntityToResponse(flight.getDepartureLocation()));
        response.arrivalLocation(LOCATION_MAPPER_INSTANCE.mapEntityToResponse(flight.getArrivalLocation()));
    }
}
