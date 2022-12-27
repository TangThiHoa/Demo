package com.example.task.service;
import com.example.task.Entity.TaskDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskDetailService extends IGeneralService<TaskDetail> {
    int sumEstimate(Long id);

    List<TaskDetail> findByIds(@Param("id") Long id);

}
