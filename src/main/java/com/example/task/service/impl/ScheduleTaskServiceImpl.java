package com.example.task.service.impl;
import com.example.task.Entity.ScheduleTask;
import com.example.task.Entity.TaskDetail;
import com.example.task.Repository.ScheduleTaskRepository;
import com.example.task.Repository.TaskDetailRepository;
import com.example.task.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    @Autowired
    ScheduleTaskRepository taskRepository;

    @Autowired
    TaskDetailRepository taskDetailRepository;

    @Override
    public List<ScheduleTask> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public ScheduleTask findById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void save(ScheduleTask scheduleTask) {
        taskRepository.save(scheduleTask);
    }
    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);

    }

    @Override
    public int totalTime(Long taskId) {
        return taskRepository.totalTime(taskId);
    }

    @Override
    public List<ScheduleTask> findScheduleTaskByIdTask(Long taskId) {
        return taskRepository.findScheduleTaskByIdTask(taskId);
    }

    @Override
    public List<TaskDetail> findTaskDetailByTaskId(Long taskId) {
        return taskRepository.findTaskDetailByTaskId(taskId);
    }


}
