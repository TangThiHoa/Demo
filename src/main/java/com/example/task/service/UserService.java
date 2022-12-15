package com.example.task.service;

import com.example.task.Entity.User;
import com.example.task.request.UserLoginRequest;
import com.example.task.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    void saveUser(UserRequest userRequest);

    User findUserByEmail(String email);

    List<Object> findAllUsers();

    boolean isRegister(User user);

    Iterable<User> findAll();

    boolean checkLogin(UserLoginRequest user);

    boolean isCorrectConfirmPassword(UserRequest userRequest);
}
