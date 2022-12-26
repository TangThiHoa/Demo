package com.example.task.controller;

import com.example.task.Entity.Project;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@RequestMapping("project")
public class AddProjectController {
    @Autowired
    ProjectService projectService;
    @GetMapping("/add")
    public ModelAndView addFormProject() {
        ModelAndView modelAndView = new ModelAndView("addProject");
        modelAndView.addObject("project", new Project());
        return modelAndView;
    }
    @PostMapping("/create")
    public String saveProject(@Valid @ModelAttribute("project") Project project, Model model) {
        projectService.save(project);
        model.addAttribute("project", new Project());
        model.addAttribute("projects", projectService.findAll());
        return "listProject";
    }
}
