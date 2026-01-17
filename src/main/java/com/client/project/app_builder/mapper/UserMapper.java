package com.client.project.app_builder.mapper;

import com.client.project.app_builder.dto.auth.SignupRequest;
import com.client.project.app_builder.dto.auth.UserProfileResponse;
import com.client.project.app_builder.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);
    UserProfileResponse toUserProfileResponse(User user);
}
