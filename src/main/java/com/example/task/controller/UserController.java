package com.example.task.controller;
import com.example.task.Entity.User;
import com.example.task.Repository.UserRepository;
import com.example.task.request.UserRequest;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/list")
    public ModelAndView listUser() {
        List<User> users = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("/listUser");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("userDetail");
        modelAndView.addObject("users", userService.findById(id));
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject("userEdit", userService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("userEdit") UserRequest user) {
        User service = userService.findById(id);
        service.setUsername(user.getUserName());
        service.setPassword(passwordEncoder.encode(user.getPassword()));
        service.setEmail(user.getEmail());
        service.setConfirmPassword(user.getConfirmPassword());
        userRepository.save(service);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id, ModelAndView modelAndView) {
        userService.remove(id);
        List<User> users = userService.findAll();
        modelAndView = new ModelAndView("/user");
        modelAndView.addObject("userList", users);
        return modelAndView;
    }



}
