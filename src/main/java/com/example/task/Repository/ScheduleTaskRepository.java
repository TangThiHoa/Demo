package com.example.task.Repository;

import com.example.task.Entity.ScheduleTask;
import com.example.task.Entity.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM schedule_task st where st.task_id =:taskId")
    void deleteTaskId(@Param("taskId") Long taskId);

    @Query(nativeQuery = true, value = " select sum(work_time) from schedule_task join task t on t.id = schedule_task.task_id where task_id =:taskId ")
    int totalTime(@Param("taskId") Long taskId);

    @Query(nativeQuery = true, value = "select * from schedule_task st join task t on t.id = st.task_id where t.id = :taskId ")
    List<ScheduleTask> findScheduleTaskByIdTask(@Param("taskId") Long taskId);

    @Query(nativeQuery = true, value = " select * from task_detail where task_id=:taskId ")
    List<TaskDetail> findTaskDetailByTaskId(@Param("taskId") Long taskId);

}
