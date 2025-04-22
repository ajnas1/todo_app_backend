package com.example.todo_backend.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_backend.model.TodoModel;
import com.example.todo_backend.service.TodoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("todos")
public class TodoController {
    @Autowired
    TodoService todoService;
    @PostMapping("create")
    public ResponseEntity<String> createTask (@Valid @RequestBody TodoModel todo) {
        return todoService.createTask(todo);
    }

    @GetMapping("getAllTasks")
    public ResponseEntity<List<TodoModel>> getAllTasks() {
        return todoService.getAllTasks();
    }
    

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
        return todoService.deleteTask(id);
    }

    @PatchMapping("update")
    public ResponseEntity<TodoModel> updateTask(@RequestBody TodoModel todo) {
        return todoService.updateTask(todo);
    }

    @GetMapping("getCompletedTask")
    public ResponseEntity<List<TodoModel>> getCompletedTask() {
        return todoService.getCompletedTask();
    }

    @PutMapping("toggleTaskStatus")
    public ResponseEntity<String> toggleTaskStatus(@RequestBody TodoModel todo) {
        return todoService.toggleTaskStatus(todo);
    }

    @GetMapping("getTaskById/{id}")
    public ResponseEntity<TodoModel> getTaskById(@PathVariable Integer id) {
        return todoService.getTaskById(id);
    }
    
}