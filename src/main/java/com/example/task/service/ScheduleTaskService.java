package com.example.task.service;

import com.example.task.Entity.ScheduleTask;
import com.example.task.Entity.TaskDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleTaskService extends IGeneralService<ScheduleTask> {
    int totalTime(@Param("taskId") Long taskId);

    List<ScheduleTask> findScheduleTaskByIdTask(@Param("taskId") Long taskId);

    List<TaskDetail> findTaskDetailByTaskId(@Param("taskId") Long taskId);
}
