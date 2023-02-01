package com.example.task.controller;
import com.example.task.Entity.Project;
import com.example.task.service.ProjectService;
import com.example.task.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping("/add")
    public String addFormProject(Model model) {
        model.addAttribute("project", new Project());
        return "project/addProject";
    }

    @PostMapping("/create")
    public String saveProject(@Valid @ModelAttribute("project") Project project, Model model) {
        projectService.save(project);
        model.addAttribute("project", new Project());
        model.addAttribute("projectList", projectService.findAll());
        return "project/listProject";
    }

    @GetMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable Long projectId, Model model) {
        projectServiceImpl.deleteProjectId(projectId);
        List<Project> taskList = projectService.findAll();
        model.addAttribute("projectList", taskList);
        return "redirect:project/listProject";
    }

}
