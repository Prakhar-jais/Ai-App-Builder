package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.subscription.CheckoutRequest;
import com.client.project.app_builder.dto.subscription.CheckoutResponse;
import com.client.project.app_builder.dto.subscription.PortalResponse;
import com.client.project.app_builder.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
     SubscriptionResponse getCurrentSubscription(Long userId);

     CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

     PortalResponse openCustomerPortal(Long userId);
}
