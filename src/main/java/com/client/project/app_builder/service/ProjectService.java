package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.project.ProjectRequest;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {

     List<ProjectSummaryResponse> getUserProjects(Long userId);

     ProjectResponse getUserProjectById(Long id, Long userId);

     ProjectResponse createProject(ProjectRequest request, Long userId);

     ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    void softDelete(Long id, Long userId);
}
