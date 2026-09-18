package iki.fordow.repository;

import iki.fordow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String emil);
    boolean existsByEmailAndIdNot(String email,Long userId);

    @Query("Select u from User u " +
            "join u.role r where r.name='ROLE_USER'  ")
    List<User> getAllMembers();
}
