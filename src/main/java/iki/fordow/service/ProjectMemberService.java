package iki.fordow.service;

import iki.fordow.dto.ProjectMemberDto;
import iki.fordow.dto.ProjectMemberProjectionDto;

import java.util.List;

public interface ProjectMemberService {
    void addMember(Long projectId, Long memberId);
    List<ProjectMemberProjectionDto> getAllMemberAdded(Long projectId);
    void deleteByMemberAndProject(Long memberId,Long projectId);
    List<ProjectMemberProjectionDto> getAllProjectUserEnrolledAsMember();
}
