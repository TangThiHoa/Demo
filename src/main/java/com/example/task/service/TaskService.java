package com.example.task.service;

import com.example.task.Entity.Project;
import com.example.task.Entity.Task;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskService extends IGeneralService<Task> {
    List<Task> findByTaskId(@Param("user_id") Long id);
    List<Task> findByProjectId(@Param("project_id") Long id);

}
