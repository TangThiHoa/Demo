package com.example.task.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Schedule_task")
public class ScheduleTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Task task;
    @Column(name = "time_work")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeWork;

}
