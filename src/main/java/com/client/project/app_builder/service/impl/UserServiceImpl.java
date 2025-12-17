package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.auth.UserProfileResponse;
import com.client.project.app_builder.dto.subscription.PlanLimitsResponse;
import com.client.project.app_builder.dto.subscription.UsageTodayResponse;
import com.client.project.app_builder.service.UsageService;
import com.client.project.app_builder.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
