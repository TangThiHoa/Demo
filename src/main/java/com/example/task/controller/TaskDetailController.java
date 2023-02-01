package com.example.task.controller;
import com.example.task.Entity.TaskDetail;
import com.example.task.Repository.TaskDetailRepository;
import com.example.task.service.TaskDetailService;
import com.example.task.service.TaskService;
import com.example.task.service.impl.ScheduleTaskServiceImpl;
import com.example.task.service.impl.TaskDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/taskDetail")
public class TaskDetailController {
    @Autowired
    TaskDetailService taskDetailService;
    @Autowired
    TaskDetailServiceImpl taskDetailServiceImpl;
    @Autowired
    TaskDetailRepository taskDetailRepository;
    @Autowired
    ScheduleTaskServiceImpl scheduleTaskService;
    @Autowired
    TaskService taskService;
    @GetMapping("add")
    public String addForm(Model model) {
        model.addAttribute("addTask", new TaskDetail());
        model.addAttribute("task", taskService.findAll());
        return "taskDetail/addTaskDetail";
    }
    @PostMapping("/newList")
    public String saveTaskDetail(@Valid @ModelAttribute TaskDetail taskDetail) {
        taskDetailService.save(taskDetail);
        scheduleTaskService.save(taskDetail);
        return "redirect:taskDetail/listTaskDetail";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("updateTaskDetail", taskDetailService.findById(id));
        model.addAttribute("task", taskDetailService.findAll());
        return "taskDetail/editTaskDetail";
    }
    @PostMapping("/list/{id}")
    public String updateTaskDetail(@ModelAttribute("updateTaskDetail") TaskDetail taskDetail, @PathVariable Long id, Model model) {
        TaskDetail updateTaskDetail = taskDetailService.findById(id);
        updateTaskDetail.setTask(taskDetail.getTask());
        updateTaskDetail.setLinkProject(taskDetail.getLinkProject());
        updateTaskDetail.setLinkTask(taskDetail.getLinkTask());
        updateTaskDetail.setDescription(taskDetail.getDescription());
        updateTaskDetail.setNote(taskDetail.getNote());
        updateTaskDetail.setPoint(taskDetail.getPoint());
        updateTaskDetail.setEstimateTime(taskDetail.getEstimateTime());
        updateTaskDetail.setRealTime(taskDetail.getRealTime());
        updateTaskDetail.setCreateDate(taskDetail.getCreateDate());
        updateTaskDetail.setCreateBy(taskDetail.getCreateBy());
        updateTaskDetail.setUpdateBy(taskDetail.getUpdateBy());
        updateTaskDetail.setUpdateDate(taskDetail.getUpdateDate());
        taskDetailService.save(updateTaskDetail);
        scheduleTaskService.save(taskDetail);
        List<TaskDetail> detailList = taskDetailService.findAllTask();
        model.addAttribute("listTaskDetails", detailList);
        return "taskDetail/listTaskDetail";
    }
    @GetMapping("/listDetail/{id}")
    public String detailTask(@PathVariable Long id, Model model) {
        model.addAttribute("sum", taskDetailService.sumEstimate(id));
        model.addAttribute("listTaskId", taskDetailRepository.findTaskByTaskId(id));
        return "taskDetail/taskDetailFull";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Model model) {
        taskDetailServiceImpl.deleteTaskById(id);
        List<TaskDetail> detailList = taskDetailService.findAllTask();
        model.addAttribute("listTaskDetails", detailList);
        return "taskDetail/listTaskDetail";
    }
}
