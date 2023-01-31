package com.example.task.Repository;

import com.example.task.Entity.Project;
import com.example.task.Entity.Task;
import com.example.task.response.ProjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(nativeQuery = true, value = "select * from project join task t on project.id = t.project_id where project_id = :project_id ")
    List<Project> findTaskByProjectId(@Param("project_id") Long projectId);

    @Query(nativeQuery = true, value = "select * from project join task t on project.id = t.project_id where user_id =:user_id ")
    List<Project> findTaskByUserId(@Param("user_id") Long userId);
}
