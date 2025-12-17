package com.client.project.app_builder.repository;

import com.client.project.app_builder.entity.ProjectMember;
import com.client.project.app_builder.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository <ProjectMember, ProjectMemberId>{
    List<ProjectMember> findByIdProjectId(Long projectId);
}
