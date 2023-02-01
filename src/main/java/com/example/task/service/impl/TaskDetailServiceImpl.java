package com.example.task.service.impl;
import com.example.task.Entity.TaskDetail;
import com.example.task.Repository.ScheduleTaskRepository;
import com.example.task.Repository.TaskDetailRepository;
import com.example.task.service.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDetailServiceImpl implements TaskDetailService {
    @Autowired
    TaskDetailRepository detailRepository;
    @Autowired
    ScheduleTaskRepository scheduleTaskRepository;

    @Autowired
    TaskDetailRepository taskDetailRepository;

    @Override
    public List<TaskDetail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public TaskDetail findById(Long id) {
        return detailRepository.findById(id).get();
    }

    @Override
    public void save(TaskDetail taskDetail) {
        detailRepository.save(taskDetail);

    }
    @Override
    public int sumEstimate(Long id) {
        List<TaskDetail> taskDetails = detailRepository.findByIds(id);
        int sum = 0;
        for (TaskDetail item : taskDetails) {
            sum += item.getEstimateTime();
        }
        return sum;
    }


    @Override
    public List<TaskDetail> findAllTask() {
        return detailRepository.findAllTask();
    }


    public void deleteTaskById(Long taskId){
        List<TaskDetail> tasks = taskDetailRepository.findTaskByTaskId(taskId);
        for (TaskDetail task : tasks) {
            scheduleTaskRepository.deleteTaskId(task.getId());
        }
        detailRepository.deleteById(taskId);
    }


}
