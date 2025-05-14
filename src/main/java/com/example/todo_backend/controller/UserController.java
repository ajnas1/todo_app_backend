package com.example.todo_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_backend.model.UserModel;
import com.example.todo_backend.service.JwtService;
import com.example.todo_backend.service.UserInfoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("addNewUser")
    public String addNewUser(@Valid @RequestBody UserModel userModel) {
        return  userInfoService.addUser(userModel);
    }
    
}
