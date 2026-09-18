package iki.fordow.repository;

import iki.fordow.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name,Long roleId);
    Role findByName(String roleName);
}
