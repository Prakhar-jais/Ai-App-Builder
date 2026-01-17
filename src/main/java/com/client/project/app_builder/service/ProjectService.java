package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.project.ProjectRequest;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {

     List<ProjectSummaryResponse> getUserProjects();

     ProjectResponse getUserProjectById(Long id);

     ProjectResponse createProject(ProjectRequest request);

     ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);
}
