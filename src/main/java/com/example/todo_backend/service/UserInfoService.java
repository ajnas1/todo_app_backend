package com.example.todo_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todo_backend.dao.UserInfoDao;
import com.example.todo_backend.exception.EmailAlreadyExistsException;
import com.example.todo_backend.model.RegisterReponseModel;
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


    //for register a new user
    public ResponseEntity<?> addUser(UserModel userInfo) {

        //check the user email already exists 
        if(!userDao.existsByEmail(userInfo.getEmail())) {
            //encode the password 
            String encodePassword = encoder.encode(userInfo.getPassword());

            //assing the encoded password to password and conform password field
            userInfo.setPassword(encodePassword);   

            //because custom password matcher only valid when we save this data to model
            userInfo.setConformPassword(encodePassword);

            //save the data
            UserModel savedUser = userDao.save(userInfo);

            //create a response
            RegisterReponseModel  response = new RegisterReponseModel(
                "User registered successfully", 
                HttpStatus.ACCEPTED.value(), 
                savedUser.getId()
            );

            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }

        //throw custom email already exits exception
        throw new EmailAlreadyExistsException("User with "+userInfo.getEmail()+" is already exist");
    }
}
