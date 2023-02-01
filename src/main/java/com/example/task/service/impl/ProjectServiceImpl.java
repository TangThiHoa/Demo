package com.example.task.service.impl;
import com.example.task.Entity.Project;
import com.example.task.Entity.Task;
import com.example.task.Repository.ProjectRepository;
import com.example.task.Repository.ScheduleTaskRepository;
import com.example.task.Repository.TaskDetailRepository;
import com.example.task.Repository.TaskRepository;
import com.example.task.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskDetailRepository taskDetailRepository;
    @Autowired
    ScheduleTaskRepository scheduleTaskRepository;
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
    public void deleteProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        for (Task task : tasks) {
            taskDetailRepository.deleteTaskId(task.getId());
            scheduleTaskRepository.deleteTaskId(task.getId());
        }
        taskRepository.deleteProjectId(projectId);
        projectRepository.deleteById(projectId);
    }
}
