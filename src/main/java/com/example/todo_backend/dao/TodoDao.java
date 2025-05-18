package com.example.todo_backend.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.todo_backend.model.entity.TodoModel;

@Repository
public interface TodoDao extends JpaRepository<TodoModel,Integer>{

    List<TodoModel> findByCompleted(boolean completed);

    List<TodoModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title,String description);

    List<TodoModel> findAllByOrderByCreatedAtAsc();

    @Query(value = "SELECT * FROM todo ORDER BY CASE priority WHEN 'HIGH' THEN 1 WHEN 'MEDIUM' THEN 2 WHEN 'LOW' THEN 3 ELSE 4 END  ", nativeQuery = true)
    List<TodoModel> findAllSortedByPriority();
    
}
