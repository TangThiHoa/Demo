package com.example.task.controller;

import com.example.task.Entity.Project;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("project")
public class ListProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public ModelAndView listProject(){
        List<Project> projects = projectService.findAll();
        ModelAndView modelAndView = new ModelAndView("/listProject");
        modelAndView.addObject("projects",projects);
        return modelAndView;
    }

}
