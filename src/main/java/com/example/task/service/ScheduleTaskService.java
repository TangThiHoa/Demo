package com.example.task.service;
import com.example.task.Entity.ScheduleTask;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ScheduleTaskService extends IGeneralService<ScheduleTask> {
    List<ScheduleTask> findScheduleTaskByIdTask(@Param("taskId") Long taskId);
}
