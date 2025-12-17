package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.auth.UserProfileResponse;
import com.client.project.app_builder.dto.project.ProjectRequest;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import com.client.project.app_builder.entity.Project;
import com.client.project.app_builder.entity.User;
import com.client.project.app_builder.error.ResourceNotFoundException;
import com.client.project.app_builder.mapper.ProjectMapper;
import com.client.project.app_builder.repository.ProjectRepository;
import com.client.project.app_builder.repository.UserRepository;
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

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());
        return projectMapper.toListOfProjectSummaryResponse(projectRepository.findAllAccessibleByUser(userId));
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {

        Project project = projectRepository.findAccessibleProjectById(id,userId).orElseThrow();
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();
        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);

//        return new ProjectResponse(project.getId(),project.getName(),project.getCreatedAt(),project.getUpdatedAt(),new UserProfileResponse(project.getOwner().getId(),project.getOwner().getName(),project.getOwner().getEmail(),project.getOwner().getAvatarUrl()));
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {

        Project project = projectRepository.findAccessibleProjectById(id,userId).orElseThrow();
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to update");
        }
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);

    }

    // Internal Functions
    public Project getAccessibleProjectById(Long projectId,Long userId){
        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow(()->new ResourceNotFoundException("Project",projectId.toString()));
    }
}
