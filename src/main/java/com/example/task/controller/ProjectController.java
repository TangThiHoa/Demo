package com.example.task.controller;

import com.example.task.Entity.Project;
import com.example.task.Entity.Task;
import com.example.task.service.ProjectService;
import com.example.task.service.TaskService;
import com.example.task.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping("/listProject")
    public String listProject(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projectList", projects);
        return "/listProject";
    }

    @GetMapping("/add")
    public String addFormProject(Model model) {
        model.addAttribute("project", new Project());
        return "addProject";
    }

    @PostMapping("/create")
    public String saveProject(@Valid @ModelAttribute("project") Project project, Model model) {
        projectService.save(project);
        model.addAttribute("project", new Project());
        model.addAttribute("projectList", projectService.findAll());
        return "listProject";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("editProject");
        modelAndView.addObject("projects", projectService.findById(id));
        return modelAndView;

    }

    @PostMapping("/update/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute("projects") Project project, Model model) {
        Project projectServiceById = projectService.findById(id);
        projectServiceById.setProjectName(project.getProjectName());
        projectServiceById.setDescription(project.getDescription());
        projectService.save(projectServiceById);
        model.addAttribute("projectList", projectService.findAll());
        return "listProject";
    }
    @GetMapping("/detail/{id}")
    public ModelAndView detailProject(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projectDetail");
        modelAndView.addObject("projects", projectService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable Long projectId, Model model) {
        projectServiceImpl.deleteProjectId(projectId);
        List<Project> taskList = projectService.findAll();
        model.addAttribute("projectList", taskList);
        return "/listProject";
    }

}
