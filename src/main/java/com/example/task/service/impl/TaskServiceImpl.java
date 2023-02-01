package com.example.task.service.impl;

import com.example.task.Entity.Task;
import com.example.task.Repository.ScheduleTaskRepository;
import com.example.task.Repository.TaskDetailRepository;
import com.example.task.Repository.TaskRepository;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ScheduleTaskRepository scheduleTaskRepository;


    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);

    }
    public List<Task> findByProjectIdAndTaskId(Long id) {
        List<Task> tasks = taskRepository.findByProjectId(id);
        for (Task task : tasks) {
            task.setTotalAmount(scheduleTaskRepository.totalTime(task.getId()));
        }
        return tasks;
    }


}

