package com.example.todo_backend.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo_backend.model.TodoModel;

@Repository
public interface TodoDao extends JpaRepository<TodoModel,Integer>{

    List<TodoModel> findByCompleted(boolean completed);

    
}
