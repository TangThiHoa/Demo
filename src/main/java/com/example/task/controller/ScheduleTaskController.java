package com.example.task.controller;
import com.example.task.Entity.ScheduleTask;
import com.example.task.service.ScheduleTaskService;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/scheduleTask")
public class ScheduleTaskController {
    @Autowired
    ScheduleTaskService scheduleTaskService;
    @Autowired
    TaskService taskService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newScheduleTask", new ScheduleTask());
        model.addAttribute("task", taskService.findAll());
        return "addScheduleTask";
    }

    @PostMapping("/newList")
    public String saveScheduleTask(@Valid @ModelAttribute("newScheduleTask") ScheduleTask scheduleTask, Model model) {
        scheduleTaskService.save(scheduleTask);
        List<ScheduleTask> scheduleTasks = scheduleTaskService.findAll();
        model.addAttribute("listSchedule", scheduleTasks);
        return "listSchedule";

    }

    @GetMapping("/list")
    public String listScheduleTask(Model model) {
        List<ScheduleTask> scheduleTasks = scheduleTaskService.findAll();
        model.addAttribute("listSchedule", scheduleTasks);
        return "listSchedule";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        scheduleTaskService.remove(id);
        List<ScheduleTask> scheduleTasks = scheduleTaskService.findAll();
        model.addAttribute("listSchedule", scheduleTasks);
        return "listSchedule";

    }

    @GetMapping("/detail/{id}")
    public String detailSchedule(@PathVariable Long id, Model model) {
        model.addAttribute("detailSchedule", scheduleTaskService.findById(id));
        return "detailSchedule";

    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("updateSchedule", scheduleTaskService.findById(id));
        model.addAttribute("task", taskService.findAll());
        return "editSchedule";
    }

    @PostMapping("/list/{id}")
    public String updateSchedule(@ModelAttribute("updateSchedule") ScheduleTask scheduleTask, @PathVariable Long id, Model model) {
        ScheduleTask updateSchedule = scheduleTaskService.findById(id);
        updateSchedule.setTask(scheduleTask.getTask());
        updateSchedule.setTimeWork(scheduleTask.getTimeWork());
        scheduleTaskService.save(updateSchedule);
        List<ScheduleTask> scheduleTasks = scheduleTaskService.findAll();
        model.addAttribute("listSchedule", scheduleTasks);
        return "listSchedule";

    }


}
