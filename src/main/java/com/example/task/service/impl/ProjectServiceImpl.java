package com.example.task.service.impl;

import com.example.task.Entity.Project;
import com.example.task.Repository.ProjectRepository;
import com.example.task.Repository.TaskRepository;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);

    }

    @Override
    public void remove(Long id) {
        projectRepository.deleteById(id);

    }
}
