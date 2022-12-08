package com.tutorials.msbooking.mapper;

import com.tutorials.msbooking.entity.Location;
import com.tutorials.msbooking.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class LocationMapper {

    public static final LocationMapper LOCATION_MAPPER_INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "id", source = "externalId")
    public abstract LocationModel mapEntityToResponse(Location location);
}