package com.example.task.service;

import com.example.task.Entity.User;
import com.example.task.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {

    void saveUser(UserRequest userRequest);

    User findUserByEmail(String email);

    boolean isCorrectConfirmPassword(UserRequest userRequest);

    User findById(Long id);
    void remove(Long id);

    UserDetails loadUserById(Long id) throws UsernameNotFoundException;

    List<User> findAll();



}
