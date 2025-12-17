package com.client.project.app_builder.dto.project;

import com.client.project.app_builder.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(Long id, String name, Instant createdAt, Instant updatedAt, UserProfileResponse owner) {
}
