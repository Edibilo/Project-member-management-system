package iki.fordow.service.ServiceImpl;

import iki.fordow.dto.UserDto;
import iki.fordow.entity.Role;
import iki.fordow.entity.User;
import iki.fordow.repository.RoleRepository;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public String register(UserDto userDto) {
        User user;
        Role role;
        if(userDto.getId()==null){
            if(userRepository.existsByEmail(userDto.getEmail())){
                throw new RuntimeException("Email Already Used !");
            }
            user=new User();
            role=roleRepository.findByName("ROLE_USER");
        }else {
            if(userRepository.existsByEmailAndIdNot(userDto.getEmail(), userDto.getId())){
                throw new RuntimeException("Email Already Used !");
            }
            user=userRepository.findById(userDto.getId()).orElseThrow(
                    () -> new RuntimeException("User Not Found !")
            );
            role=roleRepository.findById(userDto.getRoleId()).orElseThrow(
                    () -> new RuntimeException("Role Id Not Found !")
            );
        }

        user.setRole(role);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(userDto.getStatus());
        userRepository.save(user);
        return "Saved Successfully !";
    }
}
