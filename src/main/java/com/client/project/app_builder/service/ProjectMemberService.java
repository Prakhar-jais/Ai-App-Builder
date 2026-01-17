package com.client.project.app_builder.service;

import com.client.project.app_builder.dto.member.InviteMemberRequest;
import com.client.project.app_builder.dto.member.MemberResponse;
import com.client.project.app_builder.dto.member.UpdateMemberRoleRequest;
import com.client.project.app_builder.entity.ProjectMember;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectMemberService {
     List<MemberResponse> getProjectMembers(Long projectId);

     MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

     void removeProjectMember(Long projectId, Long memberId);
}
