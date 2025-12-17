package com.client.project.app_builder.entity;

import java.time.Instant;

public class UsageLog {
    Long id;
    User user;
    Project project;
    String action;
    Integer tokensUsed;
    Integer durationMs;
    Instant createdAt;
    String metaData;//{json of model used , prompt used}
}
