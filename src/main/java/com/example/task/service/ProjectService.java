package com.example.task.service;
import com.example.task.Entity.Project;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectService extends IGeneralService<Project> {
    List<Project> findTaskByProjectId(@Param("project_id") Long projectId);



}
