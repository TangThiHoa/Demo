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
    ScheduleTaskRepository scheduleTaskRepository;

    @Autowired
    TaskDetailRepository taskDetailRepository;

    @Override
    public List<ScheduleTask> findAll() {
        return scheduleTaskRepository.findAll();
    }

    @Override
    public ScheduleTask findById(Long id) {
        return scheduleTaskRepository.findById(id).get();
    }

    @Override
    public void save(ScheduleTask scheduleTask) {
        scheduleTaskRepository.save(scheduleTask);
    }

    @Override
    public void remove(Long id) {
        scheduleTaskRepository.deleteById(id);

    }

    @Override
    public int totalTime(Long taskId) {
        return scheduleTaskRepository.totalTime(taskId);
    }

    @Override
    public List<ScheduleTask> findScheduleTaskByIdTask(Long taskId) {
        return scheduleTaskRepository.findScheduleTaskByIdTask(taskId);
    }

    @Override
    public List<TaskDetail> findTaskDetailByTaskId(Long taskId) {
        return scheduleTaskRepository.findTaskDetailByTaskId(taskId);
    }

    public ScheduleTask save(TaskDetail taskDetail) {
        ScheduleTask save = new ScheduleTask();
        save.setTask(taskDetail.getTask());
        save.setWorkDate(taskDetail.getUpdateDate().toLocalDate());
        save.setWorkTime(Integer.parseInt(taskDetail.getRealTime()));
        return scheduleTaskRepository.save(save);
    }


}
