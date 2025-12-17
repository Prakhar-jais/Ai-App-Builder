package com.client.project.app_builder.service.impl;

import com.client.project.app_builder.dto.member.InviteMemberRequest;
import com.client.project.app_builder.dto.member.MemberResponse;
import com.client.project.app_builder.dto.member.UpdateMemberRoleRequest;
import com.client.project.app_builder.entity.Project;
import com.client.project.app_builder.entity.ProjectMember;
import com.client.project.app_builder.entity.ProjectMemberId;
import com.client.project.app_builder.entity.User;
import com.client.project.app_builder.mapper.ProjectMapper;
import com.client.project.app_builder.mapper.ProjectMemberMapper;
import com.client.project.app_builder.repository.ProjectMemberRepository;
import com.client.project.app_builder.repository.ProjectRepository;
import com.client.project.app_builder.repository.UserRepository;
import com.client.project.app_builder.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {

        Project project = getAccessibleProjectById(projectId,userId);
        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponseList.addAll(
                projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromMember)
                        .toList()
        );

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();
        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Can not invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,invitee.getId());
        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException(" Can not invite once again");
        }
        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .projectRole(request.role())
                .user(invitee)
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);


        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException(" Project Member with this ID does not exist");
        }
        projectMemberRepository.deleteById(projectMemberId);

    }

    // INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long projectId, Long userId){
        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow();
    }
}


