package com.client.project.app_builder.dto.member;

import com.client.project.app_builder.entity.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record  UpdateMemberRoleRequest(@NotNull ProjectRole role) {
}
