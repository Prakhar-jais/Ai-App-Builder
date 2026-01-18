package com.client.project.app_builder.security;


import com.client.project.app_builder.entity.ProjectPermissions;
import com.client.project.app_builder.entity.ProjectRole;
import com.client.project.app_builder.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {


    private  final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    private boolean hasPermission(Long projectId,ProjectPermissions projectPermissions){
        Long userId = authUtil.getCurrentUserId();

        return  projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role->role.getPermissions().contains(projectPermissions))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId){
//        Long userId = authUtil.getCurrentUserId();
//        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId,userId).
//        map(role -> role.getPermissions().contains(ProjectPermissions.VIEW))
//                .orElse(false);
        return hasPermission(projectId,ProjectPermissions.VIEW);
    }
    public boolean canEditProject(Long projectId){

        return hasPermission(projectId,ProjectPermissions.EDIT);
    }

    public boolean canDeleteProject(Long projectId){

        return hasPermission(projectId,ProjectPermissions.DELETE);
    }

    public boolean canViewMembers(Long projectId){

        return hasPermission(projectId,ProjectPermissions.VIEW_MEMBERS);
    }
    public boolean canManageMembers(Long projectId){

        return hasPermission(projectId,ProjectPermissions.MANAGE_MEMBERS);
    }

}
