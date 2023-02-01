package com.example.task.service;
import com.example.task.Entity.TaskDetail;
import java.util.List;

public interface TaskDetailService extends IGeneralService<TaskDetail> {
    int sumEstimate(Long id);
    List<TaskDetail> findAllTask();

}
