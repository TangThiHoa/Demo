package com.example.task.controller;

import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("project")
public class DetailProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/detail/{id}")
    public ModelAndView detailProject(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("projectDetail");
        modelAndView.addObject("projects",projectService.findById(id));
        return modelAndView;
    }


}
