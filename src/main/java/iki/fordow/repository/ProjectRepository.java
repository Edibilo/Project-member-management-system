package iki.fordow.repository;

import iki.fordow.entity.Project;
import iki.fordow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findProjectByOwner(User owner);

    @Query("Select p from Project p " +
            "join p.owner o where o.id=:ownerId")
    List<Project> getOwnerProjects(Long ownerId);
}
