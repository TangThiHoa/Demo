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

    @Query(nativeQuery = true, value = "select t.id as taskId " +
            "from project p " +
            "join task t on t.project_id = p.id " +
            "join task_detail td on t.id = td.task_id " +
            "join schedule_task st on st.id = td.schedule_task_id " +
            "where p.id =:projectId ")
    List<Long> findTaskId(@Param("projectId") Long projectId);
}
