package com.example.task.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = " task_name")
    private String name;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable=false)
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="task")
    private Set<TaskDetail> taskDetails;



}
