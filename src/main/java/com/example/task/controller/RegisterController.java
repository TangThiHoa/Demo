package com.example.task.controller;
import com.example.task.Entity.User;
import com.example.task.request.UserRequest;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserRequest user = new UserRequest();
        model.addAttribute("user", user);
        return "register";
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
}
