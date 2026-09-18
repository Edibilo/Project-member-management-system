package iki.fordow.controller;

import iki.fordow.service.ProjectMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/project-member")
@Controller
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @GetMapping("{memberId}/{projectId}/delete")
    public String deleteMember(@PathVariable Long memberId, @PathVariable Long projectId){
        projectMemberService.deleteByMemberAndProject(memberId,projectId);
        return "redirect:/api/v1/projects";
    }


}
