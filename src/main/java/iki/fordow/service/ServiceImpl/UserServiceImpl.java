package iki.fordow.service.ServiceImpl;

import iki.fordow.dto.UserDto;
import iki.fordow.entity.User;
import iki.fordow.mapper.UserMapper;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User Not Found !")
        );
        User savedProfile=userRepository.save(user);
        return UserMapper.mapToUserDto(savedProfile);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllMembers() {
        List<User> users=userRepository.getAllMembers();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }
}
