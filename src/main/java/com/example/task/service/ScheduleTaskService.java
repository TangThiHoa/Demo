package com.example.task.service;

import com.example.task.Entity.ScheduleTask;
import org.springframework.data.repository.query.Param;

public interface ScheduleTaskService extends IGeneralService<ScheduleTask> {
    int totalTime(@Param("taskId") Long taskId);
}
