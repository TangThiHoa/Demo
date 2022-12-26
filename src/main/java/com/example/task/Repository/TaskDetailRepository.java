package com.example.task.Repository;

import com.example.task.Entity.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetail,Long> {
}
