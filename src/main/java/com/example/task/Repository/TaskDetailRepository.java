package com.example.task.Repository;

import com.example.task.Entity.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {
    @Query(nativeQuery = true, value = "select * FROM task_detail t where t.task_id =:id")
    List<TaskDetail> findByIds(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select sum(estimate_date)as  Total from task_detail where task_id =:taskId")
    int sumEstimate(@Param("taskId") Long taskId);



}

