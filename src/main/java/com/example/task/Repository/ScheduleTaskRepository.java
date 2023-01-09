package com.example.task.Repository;
import com.example.task.Entity.ScheduleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask,Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM schedule_task st where st.task_id =:taskId")
    void deleteTaskId(@Param("taskId") Long taskId);
}
