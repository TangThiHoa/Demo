package com.example.task.Repository;

import com.example.task.Entity.Task;
import com.example.task.Entity.TaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {
    @Query(nativeQuery = true, value = "select * FROM task_detail t where t.task_id =:id")
    List<TaskDetail> findByIds(@Param("id") Long id);

    @Query(nativeQuery = true, value = "from project join task t on project.id = t.project_id join task_detail td on t.id = td.task_id join schedule_task st on st.id = td.schedule_task_id where project_id =:id")
    List<TaskDetail> findByProjectId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * " +
            "from task_detail " +
            "join task t on t.id = task_detail.task_id " +
            "join project p on p.id = t.project_id " +
            "join schedule_task st on t.id = st.task_id " +
            "where st.task_id = :taskId " +
            " and st.work_date = task_detail.update_date ")
    List<TaskDetail> findTaskByTaskId(@Param("taskId") Long id);

    @Query(nativeQuery = true, value = "select * from project " +
            "join task t on project.id = t.project_id " +
            "join user_table ut on ut.id = t.user_id " +
            "where ut.id =:id ")
    List<TaskDetail> findAllTaskByUserId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from project join task t on project.id = t.project_id join task_detail td on t.id = td.task_id join schedule_task st on t.id = st.task_id where st.task_id =:task_id  ")
    List<TaskDetail> findAllTaskByTaskId(@Param("task_id") Long id);

    @Query(nativeQuery = true, value = "select * from project " +
            "join task t on project.id = t.project_id " +
            "join task_detail td on t.id = td.task_id " +
            "join schedule_task st on t.id = st.task_id " +
            "join user_table ut on ut.id = t.user_id " +
            "where ut.username like %:#{#user}% ")
    List<TaskDetail> findAllTaskByUserName(String user);

    @Query(nativeQuery = true, value = "select * " +
            "from project " +
            "join task t on project.id = t.project_id " +
            "join task_detail td on t.id = td.task_id " +
            "join  schedule_task st on t.id = st.task_id " +
            "where  project.project_name like 'project%'")
    List<TaskDetail> findAllTaskByProjectName(@Param("project") String project);

    @Query(nativeQuery = true, value = "select * " +
            "from task_detail join schedule_task st on  task_detail.id = st.id ")
    List<TaskDetail> findAllTask();


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM task_detail td where td.task_id =:taskId ")
    void deleteTaskId(@Param("taskId") Long taskId);

    @Query(nativeQuery = true, value = "select *from task where task.id =:task_id")
    List<Task> findByIdTask(@Param("task_id") Long id);


}

