package iki.fordow.controller;

import iki.fordow.dto.UserDto;
import iki.fordow.enumerator.GenderStatus;
import iki.fordow.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@RequestMapping("api/v1/auth")
@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("register")
    public String getUser(Model model){
        model.addAttribute("user",new UserDto());
        model.addAttribute("status", GenderStatus.values());
        return "get-user";
    }

    @PostMapping("register")
    public String saveUser(@ModelAttribute UserDto userDto,Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("user",authService.register(userDto));
        String redirect = "";
        if(userDto.getId()==null){
            redirect ="redirect:/api/v1/auth/login";
        }else if(Objects.equals(userDto.getRoleName(), "ROLE_ADMIN")){
            redirect ="redirect:/api/v1/users";
        }else{
            redirect ="redirect:/api/v1/users/profile";
        }
        return redirect;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("logout")
    public String logout(){
        return "login";
    }
}
