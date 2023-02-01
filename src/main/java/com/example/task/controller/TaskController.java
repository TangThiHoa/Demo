package com.example.task.controller;
import com.example.task.Entity.Task;
import com.example.task.service.ProjectService;
import com.example.task.service.ScheduleTaskService;
import com.example.task.service.TaskService;
import com.example.task.service.UserService;
import com.example.task.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@RequestMapping("task")
@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskServiceImpl taskServiceImpl;
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
        return "/task/addTask";
    }
    @PostMapping("/project")
    public String create(@Valid @ModelAttribute("newTask") Task task, Model model) {
        taskService.save(task);
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        return "/task/listTask";
    }
    @GetMapping("/detail/{id}")
    public String detailTask(@PathVariable Long id, Model model) {
        List<Task> taskList = taskServiceImpl.findByProjectIdAndTaskId(id);
        model.addAttribute("taskByProjectId", taskList);
        return "/task/detailTask";
    }
}
