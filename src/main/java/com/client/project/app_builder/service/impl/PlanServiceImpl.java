package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.subscription.PlanResponse;
import com.client.project.app_builder.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
