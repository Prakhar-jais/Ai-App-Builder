package com.client.project.app_builder.entity;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {

    Long id;
    User user;
    Plan plan;

    //about customer info
    String stripeCustomerId;
    String stripeSubscriptionId;

    // subscription period info
    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd;
    Instant createdAt;
    Instant updatedAt;

    SubscriptionStatus status;
}
