package com.example.task.Entity;

import lombok.*;

import javax.persistence.*;;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_name")
    private String name;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable=false)
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="task")
    private Set<TaskDetail> taskDetails;


    @OneToMany(fetch = FetchType.EAGER, mappedBy="task")
    private Set<ScheduleTask> scheduleTasks;




}
