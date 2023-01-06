package com.example.task.controller;
import com.example.task.Entity.Project;
import com.example.task.service.ProjectService;
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
    public ModelAndView updateProject(@PathVariable Long id, @ModelAttribute("projects") Project project) {
        Project projectServiceById = projectService.findById(id);
        projectServiceById.setProjectName(project.getProjectName());
        projectServiceById.setDescription(project.getDescription());
        projectService.save(projectServiceById);
        return new ModelAndView("listProject");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id, ModelAndView modelAndView) {
        projectService.remove(id);
        List<Project> projects = projectService.findAll();
        modelAndView = new ModelAndView("/listProject");
        modelAndView.addObject("projects", projects);
        return modelAndView;

    }
    @GetMapping("/detail/{id}")
    public ModelAndView detailProject(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projectDetail");
        modelAndView.addObject("projects", projectService.findById(id));
        return modelAndView;
    }


}
