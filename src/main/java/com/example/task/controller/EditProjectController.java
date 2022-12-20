package com.example.task.controller;
import com.example.task.Entity.Project;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("project")
public class EditProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id ){
        ModelAndView modelAndView = new ModelAndView("editProject");
        modelAndView.addObject("projects",projectService.findById(id));
        return modelAndView;

    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProject(@PathVariable Long id, @ModelAttribute("projects")Project project){
        Project projectServiceById = projectService.findById(id);
        projectServiceById.setName(project.getName());
        projectServiceById.setDescription(project.getDescription());
        projectService.save(projectServiceById);
        ModelAndView modelAndView = new ModelAndView("listProject");
        return modelAndView;
    }
}
