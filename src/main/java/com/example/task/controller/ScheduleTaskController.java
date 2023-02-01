package com.example.task.controller;
import com.example.task.Entity.ScheduleTask;
import com.example.task.service.ScheduleTaskService;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/scheduleTask")
public class ScheduleTaskController {
    @Autowired
    ScheduleTaskService scheduleTaskService;
    @Autowired
    TaskService taskService;
    
    @GetMapping("/detail/{id}")
    public String detailSchedule(@PathVariable Long id, Model model) {
        List<ScheduleTask> scheduleTasks = scheduleTaskService.findScheduleTaskByIdTask(id);
        model.addAttribute("detailSchedule", scheduleTasks);
        return "schedule/detailSchedule";
    }


}
