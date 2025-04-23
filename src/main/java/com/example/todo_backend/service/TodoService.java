package com.example.todo_backend.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.todo_backend.dao.TodoDao;
import com.example.todo_backend.exception.ResourceNotFoundException;
import com.example.todo_backend.model.TodoModel;

@Service
public class TodoService {
    @Autowired
    TodoDao todoDao;
    public ResponseEntity<String> createTask(TodoModel todo) {
       try {
        todoDao.save(todo);
        return new ResponseEntity<>("created",HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
       }
    }
    public ResponseEntity<List<TodoModel>> getAllTasks() {
        try {
            List<TodoModel> todos = todoDao.findAll(); 
            return new ResponseEntity<>(todos,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
    public ResponseEntity<String> deleteTask(Integer id) {
        try {
            if (todoDao.existsById(id)) {
                todoDao.deleteById(id);
                return new ResponseEntity<>("success",HttpStatus.OK);
            }
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
           return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<TodoModel> updateTask(TodoModel todo) {
        try {
            if (todoDao.existsById(todo.getId())) {
                todoDao.save(todo);
                return new ResponseEntity<>(todo,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<List<TodoModel>> getCompletedTask() {
        List<TodoModel> todos = new  ArrayList<>();
        todos = todoDao.findByCompleted(true);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    public ResponseEntity<String> toggleTaskStatus(TodoModel todo) {
        if(todoDao.existsById(todo.getId())) {
           todoDao.save(todo); 
           return new ResponseEntity<>("updated",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<TodoModel> getTaskById(Integer id) {
            TodoModel todo = todoDao.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Task not found with ID: " + id));
                return new ResponseEntity<>(todo,HttpStatus.OK);
    }
    public ResponseEntity<List<TodoModel>> searchTask(String query) {
        List<TodoModel> todos = todoDao.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query,query);
        if(todos.isEmpty()) {
            throw new  ResourceNotFoundException("No Result Found");
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    public ResponseEntity<List<TodoModel>> getAllTasksByDateRange() {
      try {
        List<TodoModel> todos = todoDao.findAllByOrderByCreatedAtAsc();
        return new ResponseEntity<>(todos,HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    public ResponseEntity<List<TodoModel>> getTasksByPriority() {
        List<TodoModel> todos = todoDao.findAllSortedByPriority();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    
}
