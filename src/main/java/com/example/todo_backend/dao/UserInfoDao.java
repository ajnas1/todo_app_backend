package com.example.todo_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_backend.model.entity.UserModel;

public interface UserInfoDao extends JpaRepository<UserModel, Integer>{
    Optional<UserModel>  findByEmail(String email);

    boolean existsByEmail(String email);

}
