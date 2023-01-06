package com.example.task.controller;

import com.example.task.Entity.TaskDetail;
import com.example.task.request.UserRequestDTO;
import com.example.task.service.ScheduleTaskService;
import com.example.task.service.TaskDetailService;
import com.example.task.service.TaskService;
import org.apache.logging.log4j.util.Strings;
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
    @Autowired
    ScheduleTaskService scheduleTaskService;

    @GetMapping("add")
    public String addForm(Model model) {
        model.addAttribute("addTask", new TaskDetail());
        model.addAttribute("task", taskService.findAll());
        model.addAttribute("scheduleTask", scheduleTaskService.findAll());
        return "addTaskDetail";
    }

    @PostMapping("/newList")
    public String saveTaskDetail(@Valid @ModelAttribute TaskDetail taskDetail, Model model) {
        taskDetailService.save(taskDetail);
        List<TaskDetail> details = taskDetailService.findAllTask();
        model.addAttribute("listTaskDetail", details);
        return "listTaskDetail";
    }

    @GetMapping("/list")
    public String listTaskDetail(Model model) {
        List<TaskDetail> detailList = taskDetailService.findAllTask();
        model.addAttribute("listTaskDetail", detailList);
        return "listTaskDetail";
    }


    @GetMapping("/delete/{id}")
    public String deleteTaskDetail(@PathVariable Long id, Model model) {
        taskDetailService.remove(id);
        List<TaskDetail> taskDetails = taskDetailService.findAll();
        model.addAttribute("listTaskId", taskDetails);
        return "/taskDetailFull";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("updateTaskDetail", taskDetailService.findById(id));
        model.addAttribute("task", taskDetailService.findAll());
        model.addAttribute("scheduleTask", scheduleTaskService.findAll());

        return "editTaskDetail";
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
        List<TaskDetail> taskDetails = taskDetailService.findAll();
        model.addAttribute("listTaskId", taskDetails);
        return "taskDetailFull";
    }

    @GetMapping("/listDetail/{id}")
    public String detailTask(@PathVariable Long id, Model model) {
        model.addAttribute("listTaskId", taskDetailService.findByIds(id));
        return "taskDetailFull";
    }

    @GetMapping("detail/{id}")
    public String detailProject(@PathVariable Long id, Model model) {
        model.addAttribute("sum", taskDetailService.sumEstimate(id));
        model.addAttribute("listByUserId", taskDetailService.findAllTaskByUserId(id));
        return "newListTaskDetail";
    }



//    @GetMapping("/search")
//    public String search(Model model, @ModelAttribute("userRequestDTO") UserRequestDTO userRequestDTO) {
//        List<TaskDetail> list = taskDetailService.findAllTaskByUserName(userRequestDTO.getFindByUser());
//        model.addAttribute("listTaskDetail", list);
//        return "listTaskDetail";
//    }


}
