package com.example.todo_backend.model;

import com.example.todo_backend.utils.eums.Priority;
import com.example.todo_backend.utils.eums.TodoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
@Entity
@Table(name = "todo")
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Id",unique = true,nullable = false )
    private Integer id;

    @NotBlank(message = "Must not be Empty and NULL")
    private String todoTitle;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "task_id",nullable = false)
    private TaskModel task;
    private String startDate;
    private String endDate;
    @Enumerated(EnumType.STRING)
    private TodoStatus status;
}
