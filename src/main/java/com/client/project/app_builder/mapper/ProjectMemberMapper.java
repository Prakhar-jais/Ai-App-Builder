package com.client.project.app_builder.mapper;


import com.client.project.app_builder.dto.member.MemberResponse;
import com.client.project.app_builder.entity.ProjectMember;
import com.client.project.app_builder.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target="userId",source = "id")
    @Mapping(target = "projectRole",constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "name",source = "user.name")

    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember );
}
