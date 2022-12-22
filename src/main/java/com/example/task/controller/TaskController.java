package com.example.task.controller;

import com.example.task.Entity.Project;
import com.example.task.Entity.Task;
import com.example.task.service.ProjectService;
import com.example.task.service.TaskService;
import com.example.task.service.UserService;
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
@RequestMapping("/task")
public class AddTaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("user", userService.findAll());
        model.addAttribute("project", projectService.findAll());
        return "addTask";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newTask") Task task , Model model) {
        model.addAttribute("user", userService.findAll());
        model.addAttribute("project", projectService.findAll());
        taskService.save(task);
        return "addTask";

    }
}
