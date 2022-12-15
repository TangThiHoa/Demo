package com.example.task.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {
    @GetMapping("/index")
    public String home() {
        return "index";
    }
}
