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
import com.example.todo_backend.model.SignInModel;
import com.example.todo_backend.model.SignUpModel;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SignUpModel> userDetail = userDao.findByEmail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public ResponseEntity<?> addUser(SignUpModel userInfo) {
        if(!userDao.existsByEmail(userInfo.getEmail())) {
           
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            userInfo.setConformPassword(userInfo.getPassword());
            userDao.save(userInfo);
            return new ResponseEntity<>(userInfo,HttpStatus.ACCEPTED);
        }

        
        throw new EmailAlreadyExistsException("User with "+userInfo.getEmail()+" is already exist");
    }
}
