package com.example.task.controller;

import com.example.task.Entity.User;
import com.example.task.Repository.UserRepository;
import com.example.task.request.UserLoginRequest;
import com.example.task.request.UserRequest;
import com.example.task.service.RoleService;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserRequest user = new UserRequest();
        model.addAttribute("user", user);
        return "/register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserRequest userRequest,
                               BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userRequest.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if (!userService.isCorrectConfirmPassword(userRequest)) {
            result.rejectValue("confirmPassword", null,
                    "Confirm Password invalid");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userRequest);
            return "/register";
        }
        userService.saveUser(userRequest);
        return "redirect:/register";
    }

    @GetMapping("/user")
    public String users(Model model, @ModelAttribute UserLoginRequest user) {
        model.addAttribute("userLogin", user);
        User email = userRepository.findByEmail(user.getEmail());
        if (ObjectUtils.isEmpty(email)) {
            return "redirect:/login";
        }
        if (!email.getPassword().equals(user.getPassword())) {
            return "redirect:/login";
        }
        model.addAttribute("userList", userRepository.findAll());
        return "/user";
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute UserLoginRequest user) {
        model.addAttribute("userLogin", user);
        return "login";
    }


}
