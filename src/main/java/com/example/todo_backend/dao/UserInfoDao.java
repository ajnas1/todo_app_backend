package com.example.todo_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_backend.model.SignUpModel;

public interface UserInfoDao extends JpaRepository<SignUpModel, Integer>{
    Optional<SignUpModel>  findByEmail(String email);

    boolean existsByEmail(String email);

}
