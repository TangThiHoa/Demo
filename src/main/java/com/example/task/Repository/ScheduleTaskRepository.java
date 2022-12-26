package com.example.task.Repository;
import com.example.task.Entity.ScheduleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTask,Long> {
}
