package com.example.todo_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todo_backend.dao.UserInfoDao;
import com.example.todo_backend.model.UserModel;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userDetail = userDao.findByEmail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(UserModel userInfo) {
        
        // Encode password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo.setConformPassword(userInfo.getPassword());
        System.out.println(userInfo.getPassword());
        System.out.println(userInfo.getConformPassword());
        userDao.save(userInfo);
        return "User Added Successfully";
    }
}
