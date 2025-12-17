package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.subscription.PlanLimitsResponse;
import com.client.project.app_builder.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
     UsageTodayResponse getTodayUsageOfUser(Long userId);

     PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
