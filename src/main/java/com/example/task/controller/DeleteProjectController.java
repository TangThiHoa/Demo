package com.example.task.controller;

import com.example.task.Entity.Project;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("project")
public class DeleteProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id,ModelAndView modelAndView){
        projectService.remove(id);
        List<Project> projects = projectService.findAll();
        modelAndView = new ModelAndView("/listProject");
        modelAndView.addObject("projects",projects);
        return modelAndView;

    }
}
