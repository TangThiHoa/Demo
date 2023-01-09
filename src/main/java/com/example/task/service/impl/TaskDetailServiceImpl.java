package com.example.task.service.impl;

import com.example.task.Entity.TaskDetail;
import com.example.task.Repository.TaskDetailRepository;
import com.example.task.service.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDetailServiceImpl implements TaskDetailService {
    @Autowired
    TaskDetailRepository detailRepository;

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
    public void remove(Long id) {
        detailRepository.deleteById(id);

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
    public List<TaskDetail> findByIds(Long id) {
        return detailRepository.findByIds(id);
    }

    @Override
    public List<TaskDetail> findByProjectId(Long id) {
        return detailRepository.findByProjectId(id);
    }

    @Override
    public List<TaskDetail> findAllTaskByUserName(String user) {
        return detailRepository.findAllTaskByUserName(user);
    }

    @Override
    public List<TaskDetail> findAllTaskByUserId(Long id) {
        return detailRepository.findAllTaskByUserId(id);
    }

    @Override
    public List<TaskDetail> findAllTaskByProjectName(String project) {
        return detailRepository.findAllTaskByProjectName(project);
    }

    @Override
    public List<TaskDetail> findAllTask() {
        return detailRepository.findAllTask();
    }

    @Override
    public List<TaskDetail> findAllTaskByTaskId(Long id) {
        return detailRepository.findAllTaskByTaskId(id);
    }


}
