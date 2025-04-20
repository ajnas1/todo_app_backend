package com.example.todo_backend.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_backend.model.TodoModel;
import com.example.todo_backend.service.TodoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("todos")
public class TodoController {
    @Autowired
    TodoService todoService;
    @PostMapping("create")
    public ResponseEntity<String> createTask(@RequestBody TodoModel todo) {
        return todoService.createTask(todo);
    }

    @GetMapping("getAllTodos")
    public ResponseEntity<List<TodoModel>> getTodos() {
        return todoService.getTodos();
    }
    

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id) {
        return todoService.deleteTaskById(id);
    }
}