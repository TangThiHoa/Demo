package com.example.task.Entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Schedule_task")
public class ScheduleTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate workDate;

    @Column(name = "work_time")
    @DateTimeFormat(pattern="yyyy-MM-dd 'T'HH:mm")
    private int workTime;


    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;
}
