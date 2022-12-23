package com.example.task.service.impl;

import com.example.task.Entity.Task;
import com.example.task.Repository.TaskRepository;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
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

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);

    }
}
