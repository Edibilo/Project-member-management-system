package iki.fordow.controller;

import iki.fordow.dto.UserDto;
import iki.fordow.entity.User;
import iki.fordow.enumerator.GenderStatus;
import iki.fordow.repository.RoleRepository;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.RoleService;
import iki.fordow.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserRepository userRepository, UserService userService, RoleService roleService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("profile")
    public String profile(Model model, Authentication authentication){
        String email= authentication.getName();
        User profile=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Not Found !")
        );
        model.addAttribute("profile",profile);
        return "get-profile";
    }

    @GetMapping("profile/edit")
    public String updateUserProfile(Authentication authentication,Model model){
        String email= authentication.getName();
        User user=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Not Found !")
        );
        model.addAttribute("user",userService.findUserById(user.getId()));
        model.addAttribute("status", GenderStatus.values());
        model.addAttribute("roles",roleService.getAllRoles());
        return "get-user";
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("roles",roleService.getAllRoles());
        return "user";
    }

    @GetMapping("{userId}/edit")
    public String editUser(Model model,@PathVariable Long userId){
        model.addAttribute("user",userService.findUserById(userId));
        model.addAttribute("status", GenderStatus.values());
        model.addAttribute("roles",roleService.getAllRoles());
        return "get-user";
    }
}


