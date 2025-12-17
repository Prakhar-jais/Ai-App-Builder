package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.subscription.PlanLimitsResponse;
import com.client.project.app_builder.dto.subscription.UsageTodayResponse;
import com.client.project.app_builder.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
