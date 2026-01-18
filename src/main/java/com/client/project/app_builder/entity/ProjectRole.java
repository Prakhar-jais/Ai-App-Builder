package com.client.project.app_builder.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {

    EDITOR(Set.of( ProjectPermissions.EDIT,ProjectPermissions.VIEW,ProjectPermissions.DELETE,ProjectPermissions.VIEW_MEMBERS)),
    VIEWER(Set.of( ProjectPermissions.VIEW,ProjectPermissions.VIEW_MEMBERS)),
    OWNER(Set.of( ProjectPermissions.EDIT,ProjectPermissions.VIEW,ProjectPermissions.DELETE,ProjectPermissions.MANAGE_MEMBERS,ProjectPermissions.VIEW_MEMBERS));

    ProjectRole(ProjectPermissions... permissions){

        this.permissions = Set.of(permissions);

    }
    private final Set<ProjectPermissions> permissions;
}
