package com.example.task.Repository;
import com.example.task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(nativeQuery = true, value = "select *from task where user_id =:user_id")
    List<Task> findByTaskId(@Param("user_id") Long id);




    @Query(nativeQuery = true, value = "select * from task where project_id =:project_id")
    List<Task> findByProjectId(@Param("project_id") Long id);

    @Query(nativeQuery = true, value = "select * from task join schedule_task st on task.id = st.task_id where task_id =:task_id")
    List<Task> findTaskByTaskId(@Param("task_id") Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM task t where t.project_id =:projectId")
    void deleteProjectId(@Param("projectId") Long projectId);
}
