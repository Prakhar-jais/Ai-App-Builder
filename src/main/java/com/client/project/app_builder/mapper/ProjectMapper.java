package com.client.project.app_builder.mapper;

import com.client.project.app_builder.dto.member.MemberResponse;
import com.client.project.app_builder.dto.project.ProjectResponse;
import com.client.project.app_builder.dto.project.ProjectSummaryResponse;
import com.client.project.app_builder.entity.Project;
import com.client.project.app_builder.entity.User;
import org.mapstruct.Mapper;

import java.lang.reflect.Member;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project>projects);


}

