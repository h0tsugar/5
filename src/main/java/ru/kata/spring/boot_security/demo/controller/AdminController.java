package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userServiceInterface) {
        this.userService = userServiceInterface;
    }

    @GetMapping()
    public String showAllUsers(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("listRoles", userService.getAllRoles());
        model.addAttribute("auth", userService.findByUserName(principal.getName()).get());
        return "admin";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        if (userService.check(user)) {
            userService.saveUser(user);
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping( "/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
}
