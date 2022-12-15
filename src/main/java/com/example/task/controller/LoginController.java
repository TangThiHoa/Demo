package com.example.task.controller;
import com.example.task.Entity.User;
import com.example.task.Repository.UserRepository;
import com.example.task.request.UserLoginRequest;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login(Model model, @ModelAttribute UserLoginRequest user) {
        model.addAttribute("userLogin", user);
        return "/login";
    }

    @GetMapping("/user")
    public String users(Model model, @Valid @ModelAttribute("userLogin") UserLoginRequest user, BindingResult result) {
        model.addAttribute("userLogin", user);
        User email = userRepository.findByEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(email)) {
            //*****
            String password = passwordEncoder.encode(user.getPassword());
            System.out.println(password);
            System.out.println(email.getPassword());
            if (!email.getPassword().equals(password)) {
                result.rejectValue("password", "null", "Password invalid");
            }
        } else {
            result.rejectValue("email", "null", "Email invalid");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/login";
        }
        model.addAttribute("userList", userRepository.findAll());
        return "/user";
    }

}
