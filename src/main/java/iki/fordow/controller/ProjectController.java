package iki.fordow.controller;

import iki.fordow.dto.ProjectDto;
import iki.fordow.dto.ProjectMemberDto;
import iki.fordow.service.ProjectMemberService;
import iki.fordow.service.ProjectService;
import iki.fordow.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("api/v1/projects")
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, ProjectMemberService projectMemberService,
                             UserService userService) {
        this.projectService = projectService;
        this.projectMemberService = projectMemberService;
        this.userService = userService;
    }

    @GetMapping
    public String getProject(Model model){
        model.addAttribute("myProjects",projectService.getProjectByOwner());
        model.addAttribute("project",new ProjectDto());
        model.addAttribute("projectsByMember",projectMemberService.getAllProjectUserEnrolledAsMember());
        return "get-project";
    }

    @PostMapping
    public String createProject(@ModelAttribute ProjectDto projectDto){
        projectService.createProject(projectDto);
        return "redirect:/api/v1/projects";
    }

    @GetMapping("{projectId}/edit")
    private String getProjectById(@PathVariable Long projectId,Model model){
        model.addAttribute("project",projectService.findProjectById(projectId));
        model.addAttribute("myProjects",projectService.getProjectByOwner());
        model.addAttribute("projectsByMember",projectMemberService.getAllProjectUserEnrolledAsMember());
        return "get-project";
    }

    @GetMapping("{projectId}/members")
    private String getMember(Model model,@PathVariable Long projectId){
        model.addAttribute("projectMember",new ProjectMemberDto());
        model.addAttribute("project",projectService.findProjectById(projectId));
        model.addAttribute("members",userService.getAllMembers());
        model.addAttribute("addedMembers",projectMemberService.getAllMemberAdded(projectId));
        return "member-project";
    }

    @GetMapping("{projectId}")
    private String getMembersPerProject(@PathVariable Long projectId,Model model){
        model.addAttribute("addedMembers",projectMemberService.getAllMemberAdded(projectId));
        model.addAttribute("project",new ProjectDto());
        model.addAttribute("myProjects",projectService.getProjectByOwner());
        model.addAttribute("projectsByMember",projectMemberService.getAllProjectUserEnrolledAsMember());
        return "get-project";
    }

    @PostMapping("{projectId}/add-member")
    private String addMember(@PathVariable Long projectId, RedirectAttributes redirectAttributes,
                             ProjectMemberDto projectMemberDto){
        try {
            projectMemberService.addMember(projectId,projectMemberDto.getMemberId());
            redirectAttributes.addFlashAttribute("success","Member Added !");
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("fail",exception.getMessage());
        }
        return "redirect:/api/v1/projects/{projectId}/members";
    }

    @GetMapping("{projectId}/delete")
    public String deleteProject(@PathVariable Long projectId,RedirectAttributes redirectAttributes){
        try {
            projectService.deleteProject(projectId);
            redirectAttributes.addFlashAttribute("success","Saved !");
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("fail", exception.getMessage());
        }

        return "redirect:/api/v1/projects";
    }

}



