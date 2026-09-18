package iki.fordow.service;

import iki.fordow.dto.ProjectDto;
import iki.fordow.entity.User;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectDto projectDto);
    List<ProjectDto> getProjectByOwner();
    ProjectDto findProjectById(Long projectId);
    void deleteProject(Long projectId);
}
