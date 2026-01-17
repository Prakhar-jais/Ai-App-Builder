package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.auth.UserProfileResponse;
import com.client.project.app_builder.dto.project.ProjectRequest;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import com.client.project.app_builder.entity.*;
import com.client.project.app_builder.error.ResourceNotFoundException;
import com.client.project.app_builder.mapper.ProjectMapper;
import com.client.project.app_builder.repository.ProjectMemberRepository;
import com.client.project.app_builder.repository.ProjectRepository;
import com.client.project.app_builder.repository.UserRepository;
import com.client.project.app_builder.security.AuthUtil;
import com.client.project.app_builder.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional

public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;


    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();

        return projectMapper.toListOfProjectSummaryResponse(projectRepository.findAllAccessibleByUser(userId));
    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = authUtil.getCurrentUserId();

        Project project = projectRepository.findAccessibleProjectById(id,userId).orElseThrow();
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();

//        User owner = userRepository.findById(userId)
//                .orElseThrow(()-> new ResourceNotFoundException("User",userId.toString()));
//
         // instead of above code use proxy object to get the owner refrence that is  for lazy loading get only refrence
        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();


        project = projectRepository.save(project);

        ProjectMemberId  projectMemberId = new ProjectMemberId(project.getId(),owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);

//        return new ProjectResponse(project.getId(),project.getName(),project.getCreatedAt(),project.getUpdatedAt(),new UserProfileResponse(project.getOwner().getId(),project.getOwner().getName(),project.getOwner().getEmail(),project.getOwner().getAvatarUrl()));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();

        Project project = projectRepository.findAccessibleProjectById(id,userId).orElseThrow();
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id,userId);

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);

    }

    // Internal Functions
    public Project getAccessibleProjectById(Long projectId,Long userId){


        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow(()->new ResourceNotFoundException("Project",projectId.toString()));
    }
}
