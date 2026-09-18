package iki.fordow.service.ServiceImpl;

import iki.fordow.dto.ProjectDto;
import iki.fordow.entity.Project;
import iki.fordow.entity.User;
import iki.fordow.mapper.ProjectMapper;
import iki.fordow.repository.ProjectRepository;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.ProjectService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createProject(ProjectDto projectDto) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String email=authentication.getName();
        User owner=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Owner Not Found !")
        );
        Project project;
        if(projectDto.getId()==null){
            project=new Project();
        }else {
            project=projectRepository.findById(projectDto.getId()).orElseThrow(
                    () -> new RuntimeException("Not Found !")
            );
            if(!owner.getId().equals(project.getOwner().getId())){
                throw new RuntimeException("ACCESS DENIED");
            }
        }
        project.setStartDate(projectDto.getStartDate());
        project.setName(projectDto.getName());
        project.setEndDate(projectDto.getEndDate());
        project.setDescription(projectDto.getDescription());
        project.setOwner(owner);
        Project savedProject=projectRepository.save(project);
        ProjectMapper.mapToProjectDto(savedProject);
    }

    @Override
    public List<ProjectDto> getProjectByOwner() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String email=authentication.getName();
        User owner=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Owner Not Found !")
        );
        List<Project> projects=projectRepository.findProjectByOwner(owner);
        return projects.stream().map(ProjectMapper::mapToProjectDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto findProjectById(Long projectId) {
        Project project=projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project Not Found !")
        );
        return ProjectMapper.mapToProjectDto(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        Project project=projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project Not Found !")
        );
        assert authentication != null;
        String email=authentication.getName();
        User owner=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Owner Not Found !")
        );
        if(!owner.getId().equals(project.getOwner().getId())){
            throw new RuntimeException("ACCESS DENIED !");
        }

        projectRepository.delete(project);
    }

}









