package com.client.project.app_builder.dto.member;

import com.client.project.app_builder.entity.ProjectRole;

import java.time.Instant;

public record MemberResponse(Long userId, String email, String name, ProjectRole projectRole, Instant invitedAt) {
}
