package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.subscription.CheckoutRequest;
import com.client.project.app_builder.dto.subscription.CheckoutResponse;
import com.client.project.app_builder.dto.subscription.PortalResponse;
import com.client.project.app_builder.dto.subscription.SubscriptionResponse;
import com.client.project.app_builder.service.SubscriptionService;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
