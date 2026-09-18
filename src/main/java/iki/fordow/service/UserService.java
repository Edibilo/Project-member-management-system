package iki.fordow.service;

import iki.fordow.dto.UserDto;
import iki.fordow.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    UserDto findUserById(Long userId);
    List<UserDto> getAllUsers();
    List<UserDto> getAllMembers();
}
