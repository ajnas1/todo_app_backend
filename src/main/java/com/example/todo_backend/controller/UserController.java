package com.example.todo_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_backend.model.LoginModel;
import com.example.todo_backend.model.UserModel;
import com.example.todo_backend.service.UserInfoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserModel userModel) {
        return  userInfoService.register(userModel);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginModel userModel) {
        return userInfoService.login(userModel);
    }
    
    
}
