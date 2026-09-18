package iki.fordow.mapper;

import iki.fordow.dto.ProjectDto;
import iki.fordow.entity.Project;

public class ProjectMapper {

    public static ProjectDto mapToProjectDto(Project project){
        ProjectDto projectDto=new ProjectDto();
        projectDto.setDescription(project.getDescription());
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setOwnerId(project.getOwner().getId());
        return projectDto;
    }

    public static Project mapToProject(ProjectDto projectDto){
        Project project=new Project();
        project.setDescription(projectDto.getDescription());
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setEndDate(projectDto.getEndDate());
        project.setStartDate(projectDto.getStartDate());
        return project;
    }
}
