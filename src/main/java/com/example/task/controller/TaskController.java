package com.example.task.controller;
import com.example.task.Entity.Task;
import com.example.task.service.ProjectService;
import com.example.task.service.ScheduleTaskService;
import com.example.task.service.TaskService;
import com.example.task.service.UserService;
import com.example.task.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("task")
@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    ScheduleTaskService scheduleTaskService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("user", userService.findAll());
        model.addAttribute("project", projectService.findAll());
        model.addAttribute("scheduleTask", scheduleTaskService.findAll());
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
    public String listTask(Model model) {
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        return "/listTask";
    }

    @GetMapping("/detail/{id}")
    public String detailTask(@PathVariable Long id, Model model) {
        model.addAttribute("taskByProjectId", taskService.findByProjectId(id));
        return "/detailTask";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("updateTask", taskService.findById(id));
        model.addAttribute("user", userService.findAll());
        model.addAttribute("project", projectService.findAll());
        model.addAttribute("scheduleTask", scheduleTaskService.findAll());
        return "editTask";
    }

    @PostMapping("/list/{id}")
    public String updateTask(@ModelAttribute("updateTask") Task task, @PathVariable Long id, Model model) {
        Task updateTask = taskService.findById(id);
        updateTask.setName(task.getName());
        updateTask.setUser(task.getUser());
        updateTask.setProject(task.getProject());
        taskService.save(updateTask);
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        return "/listTask";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Model model) {
        taskService.remove(id);
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "/listTask";
    }




}
