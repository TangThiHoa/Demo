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

    @Query(nativeQuery = true, value = "select * from project " +
            "join task t on project.id = t.project_id " +
            "join task_detail td on t.id = td.task_id " +
            "join schedule_task st on st.id = td.schedule_task_id " +
            "join user_table ut on ut.id = t.user_id "+
            "where ut.username like %:#{#user}% ")
    List<TaskDetail> findAllTaskByUserName(String user);

    @Query(nativeQuery = true, value = "select *\n" +
            "from project\n" +
            "join task t on project.id = t.project_id\n" +
            "join task_detail td on t.id = td.task_id\n" +
            "join schedule_task st on st.id = td.schedule_task_id\n" +
            "where  project.project_name like 'project%'")
    List<TaskDetail> findAllTaskByProjectName(@Param("project") String project);

    @Query(nativeQuery = true, value = "select *\n" +
            "from project\n" +
            "         join task t on project.id = t.project_id\n" +
            "         join task_detail td on t.id = td.task_id\n" +
            "         join schedule_task st on st.id = td.schedule_task_id" )
    List<TaskDetail> findAllTask();


}

