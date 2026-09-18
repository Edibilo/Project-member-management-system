package iki.fordow.repository;

import iki.fordow.dto.ProjectMemberProjectionDto;
import iki.fordow.entity.Project;
import iki.fordow.entity.ProjectMember;
import iki.fordow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {

    boolean existsByProjectAndMember(Project project, User member);

    @Query("Select new iki.fordow.dto.ProjectMemberProjectionDto(m.id,p.id,m.username,p.name,pm.joinedAt) " +
            "from ProjectMember pm " +
            "join pm.member m " +
            "join pm.project p " +
            "where p.id=:projectId ")
    List<ProjectMemberProjectionDto> allMemberEnrolledInProject(Long projectId);

    @Query("Select pm from ProjectMember pm where pm.member.id=:memberId and pm.project.id=:projectId")
    Optional<ProjectMember> getProjectMemberByMemberAndProject(Long memberId,Long projectId);

    Optional<ProjectMember> findProjectMemberByMemberAndProject(User member,Project project);

    @Query("Select new iki.fordow.dto.ProjectMemberProjectionDto(m.id,p.id,m.username,p.name,pm.joinedAt)" +
            "from ProjectMember pm " +
            "join pm.member m " +
            "join pm.project p " +
            "where m.id=:memberId ")
    List<ProjectMemberProjectionDto> getAllEnrolledProjectByMember(Long memberId);
}
