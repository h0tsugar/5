package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.exception.CustomUserException;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        if (userService.findUserByUsername(principal.getName()).isEmpty()) {
            throw new CustomUserException("User not found");
        }
        model.addAttribute("user", userService.findUserByUsername(principal.getName()).get());
        return "/user";
    }
}
