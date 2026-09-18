package iki.fordow.mapper;

import iki.fordow.dto.ProjectMemberDto;
import iki.fordow.entity.ProjectMember;

public class ProjectMemberMapper {

    public static ProjectMember mapToProjectMember(ProjectMemberDto projectMemberDto){
        ProjectMember projectMember=new ProjectMember();
        projectMember.setId(projectMember.getId());
        return projectMember;
    }

    public static ProjectMemberDto mapToProjectMemberDto(ProjectMember projectMember){
        ProjectMemberDto projectMemberDto=new ProjectMemberDto();
        projectMemberDto.setId(projectMember.getId());
        projectMemberDto.setMemberId(projectMember.getMember().getId());
        projectMemberDto.setProjectId(projectMember.getProject().getId());
        return projectMemberDto;
    }
}
