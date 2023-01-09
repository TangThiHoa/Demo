package com.example.task.service;
import com.example.task.Entity.TaskDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskDetailService extends IGeneralService<TaskDetail> {
    int sumEstimate(Long id);

    List<TaskDetail> findByIds(@Param("id") Long id);

    List<TaskDetail> findByProjectId(@Param("id") Long id);


    List<TaskDetail> findAllTaskByUserName(@Param("user") String user);

    List<TaskDetail> findAllTaskByUserId(@Param("id") Long id);


    List<TaskDetail> findAllTaskByProjectName(@Param("project") String project);

    List<TaskDetail> findAllTask();

    List<TaskDetail> findAllTaskByTaskId(@Param(" task_id ") Long id);
}
