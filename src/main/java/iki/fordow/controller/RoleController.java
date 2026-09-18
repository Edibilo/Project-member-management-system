package iki.fordow.controller;

import iki.fordow.dto.RoleDto;
import iki.fordow.entity.User;
import iki.fordow.repository.UserRepository;
import iki.fordow.service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserRepository userRepository;

    public RoleController(RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getRole(Model model){
        model.addAttribute("role",new RoleDto());
        model.addAttribute("roles",roleService.getAllRoles());
        return "get-role";
    }

    @PostMapping
    public String createRole(@ModelAttribute RoleDto roleDto, Model model,
                             RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("role",roleService.createRole(roleDto));
            redirectAttributes.addFlashAttribute("success","Successfully !");
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("fail","Try Again ! "+ exception.getMessage());
        }
        return "redirect:/api/v1/roles";
    }

    @GetMapping("{roleId}/delete")
    public String deleteRole(@PathVariable Long roleId, RedirectAttributes redirectAttributes, Authentication authentication){
        String email=authentication.getName();
        User user=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User Not Found !")
        );
        if(!Objects.equals(user.getRole().getName(), "ROLE_ADMIN")){
            redirectAttributes.addFlashAttribute("fail","Access Denied !");
            throw new RuntimeException("Access Denied !");
        }
        roleService.deleteRole(roleId);
        return "redirect:/api/v1/roles";
    }

    @GetMapping("{roleId}/edit")
    public String findRoleById(@PathVariable Long roleId,Model model,Authentication authentication,
                               RedirectAttributes redirectAttributes){
        String email=authentication.getName();
        User user=userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User Not Found !")
        );
        if(!Objects.equals(user.getRole().getName(), "ROLE_ADMIN")){
            redirectAttributes.addFlashAttribute("fail","Access Denied !");
            throw new RuntimeException("Access Denied !");
        }
        model.addAttribute("role",roleService.findByRole(roleId));
        model.addAttribute("roles",roleService.getAllRoles());
        return "get-role";
    }


}
