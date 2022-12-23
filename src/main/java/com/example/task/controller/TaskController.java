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
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/project")
    public String create(@Valid @ModelAttribute("newTask") Task task, Model model) {
        taskService.save(task);
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        return "/listTask";

    }

    @GetMapping("/list")
    public String listTask(@Valid @ModelAttribute("newTask") Task task, Model model) {
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        return "/listTask";

    }

    @GetMapping("/detail/{id}")
    public String detailTask(@PathVariable Long id,Model model){
        model.addAttribute("tasks", taskService.findById(id));
        return "/detailTask";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", taskService.findById(id));
        return "editTask";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@ModelAttribute("tasks") Task task, @PathVariable Long id, Model model) {
        Task taskServiceById = taskService.findById(id);
        taskServiceById.setName(task.getName());
        taskServiceById.setUser(task.getUser());
        taskServiceById.setProject(task.getProject());
        taskService.save(taskServiceById);
        return  "/listTask";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Model model){
        taskService.remove(id);
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks",tasks);
        return "/listTask";
    }




}
