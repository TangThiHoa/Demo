package com.example.task.controller;

import com.example.task.Entity.TaskDetail;
import com.example.task.service.TaskDetailService;
import com.example.task.service.TaskService;
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
    TaskService taskService;

    @GetMapping("add")
    public String addForm(Model model) {
        model.addAttribute("addTask", new TaskDetail());
        model.addAttribute("task", taskService.findAll());
        return "addTaskDetail";
    }

    @PostMapping("/newList")
    public String saveTaskDetail(@Valid @ModelAttribute TaskDetail taskDetail, Model model) {
        taskDetailService.save(taskDetail);
        List<TaskDetail> details = taskDetailService.findAll();
        model.addAttribute("listTaskDetail", details);
        return "listTaskDetail";
    }

    @GetMapping("list")
    public String listTaskDetail(Model model) {
        List<TaskDetail> detailList = taskDetailService.findAll();
        model.addAttribute("listTaskDetail", detailList);
        return "listTaskDetail";
    }

    @GetMapping("/detail/{id}")
    public String detailTask(@PathVariable Long id, Model model){
        model.addAttribute("taskDetail",taskDetailService.findById(id));
        return "taskDetailFull";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskDetail(@PathVariable Long id, Model model) {
        taskDetailService.remove(id);
        List<TaskDetail> taskDetails = taskDetailService.findAll();
        model.addAttribute("listTaskDetail", taskDetails);
        return "/listTaskDetail";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("updateTaskDetail",taskDetailService.findById(id));
        model.addAttribute("task",taskDetailService.findAll());
        return "editTaskDetail";
    }

    @PostMapping("/list/{id}")
    public String updateTaskDetail(@ModelAttribute("updateTaskDetail") TaskDetail taskDetail, @PathVariable Long id, Model model){
        TaskDetail updateTaskDetail = taskDetailService.findById(id);
        updateTaskDetail.setTask(taskDetail.getTask());
        updateTaskDetail.setLinkProject(taskDetail.getLinkProject());
        updateTaskDetail.setLinkTask(taskDetail.getLinkTask());
        updateTaskDetail.setDescription(taskDetail.getDescription());
        updateTaskDetail.setNote(taskDetail.getNote());
        updateTaskDetail.setPoint(taskDetail.getPoint());
        updateTaskDetail.setEstimateDate(taskDetail.getEstimateDate());
        updateTaskDetail.setRealTime(taskDetail.getRealTime());
        updateTaskDetail.setCreateDate(taskDetail.getCreateDate());
        updateTaskDetail.setCreateBy(taskDetail.getCreateBy());
        updateTaskDetail.setUpdateBy(taskDetail.getUpdateBy());
        updateTaskDetail.setUpdateDate(taskDetail.getUpdateDate());
        taskDetailService.save(updateTaskDetail);
        List<TaskDetail> taskDetails = taskDetailService.findAll();
        model.addAttribute("listTaskDetail",taskDetails);
        return "/listTaskDetail";
    }
}
