package com.tutorials.msbooking.mapper;

import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "externalId", target = "id")
    public abstract UserModel mapEntityToResponse(User user);

}
