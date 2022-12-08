package com.tutorials.msuser.mapper;

import com.tutorials.msuser.entity.User;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.UserRsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "email", source = "username")
    public abstract User mapRequestToEntity(SignupRequestModel request);

    @Mapping(target = "username", source = "email")
    @Mapping(target = "id", source = "externalId")
    public abstract UserRsModel mapEntityToResponse(User user);
}
