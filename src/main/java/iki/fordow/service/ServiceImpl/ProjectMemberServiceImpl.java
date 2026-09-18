package iki.fordow.service.ServiceImpl;

import iki.fordow.dto.ProjectMemberDto;
import iki.fordow.dto.ProjectMemberProjectionDto;
import iki.fordow.entity.Project;
import iki.fordow.entity.ProjectMember;
import iki.fordow.entity.User;
import iki.fordow.mapper.ProjectMemberMapper;
import iki.fordow.repository.ProjectMemberRepository;
import iki.fordow.repository.ProjectRepository;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.ProjectMemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectMemberServiceImpl(ProjectMemberRepository projectMemberRepository, ProjectRepository projectRepository,
                                    UserRepository userRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addMember(Long projectId, Long memberId) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String email= authentication.getName();
        User owner=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User Not Found !")
        );
        Project project=projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project Not Found !")
        );
        User member=userRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("Member Not Found !")
        );

        if(projectMemberRepository.existsByProjectAndMember(project,member)){
            throw new RuntimeException("Member Already Added to this Project !");
        }
        if(member.getId().equals(owner.getId())){
            throw new RuntimeException("Can't Add owner as Project Member !");
        }
        ProjectMember projectMember=new ProjectMember();
        projectMember.setMember(member);
        projectMember.setProject(project);
        projectMemberRepository.save(projectMember);

    }

    @Override
    public List<ProjectMemberProjectionDto> getAllMemberAdded(Long projectId) {
        return projectMemberRepository.allMemberEnrolledInProject(projectId);
    }

    @Override
    public void deleteByMemberAndProject(Long memberId, Long projectId) {
        Project project=projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Not Found !")
        );
        User member=userRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("Not Found !")
        );

        ProjectMember projectMember=projectMemberRepository.
                findProjectMemberByMemberAndProject(member,project).orElseThrow(
                () -> new RuntimeException("Not Found !")
        );
        projectMemberRepository.delete(projectMember);
    }

    @Override
    public List<ProjectMemberProjectionDto> getAllProjectUserEnrolledAsMember() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String email=authentication.getName();
        User member=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Owner Not Found !")
        );
        return projectMemberRepository.getAllEnrolledProjectByMember(member.getId());
    }

}
