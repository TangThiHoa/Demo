package com.example.task.controller;

import com.example.task.Entity.Task;
import com.example.task.Repository.ProjectRepository;
import com.example.task.Repository.UserRepository;
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
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;



    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("user", userService.findAll());
        model.addAttribute("project", projectService.findAll());
        return "addTask";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newTask") Task task) {
        taskService.save(task);
        return "listTask";

    }

    @GetMapping("/list")
    public String listTask(Model model) {
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        return "listTask";

    }
}
