package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
