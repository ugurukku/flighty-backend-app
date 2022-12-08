package com.tutorials.msflight.mapper;

import com.tutorials.msflight.entity.Location;
import com.tutorials.msflight.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class LocationMapper {

    public static final LocationMapper LOCATION_MAPPER_INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "id", source = "externalId")
    public abstract LocationModel mapEntityToResponse(Location location);
}
