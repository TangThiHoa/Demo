package com.example.task.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task_detail")
public class TaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Task task;

    @Column(name = "link_project")
    private String linkProject;
    @Column(name = "link_task")

    private String linkTask;

    private String description;

    private String note;

    private String point;
    @Column(name = "estimate_date")

    private String estimateDate;
    @Column(name = "real_time")

    private String realTime;
    @Column(name = "create_date")

    private Date createDate;
    @Column(name = "create_by")

    private String createBy;
    @Column(name = "update_date")

    private Date updateDate;
    @Column(name = "update_by")

    private String updateBy;


}
