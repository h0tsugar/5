package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.CustomUserException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }


//    public String newUser(@ModelAttribute("user") User user, Model model) {
//        List<Role> listRoles = roleService.
//    }

//    @GetMapping("/admin")
//    public String adminPanel(Model model, Principal principal) {
//        if (userService.findUserByUsername(principal.getName()).isEmpty()) {
//            throw new CustomUserException("User not found exception");
//        }
//        model.addAttribute("new_role", new Role());
//        model.addAttribute("edit_user", new User());
//        model.addAttribute("roles", roleService.getAllRole());
//        model.addAttribute("new_user", new User());
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("auth", userService.findUserByUsername(principal.getName()).get());
//        return "admin";
//    }
//
//    @PostMapping("/admin/addUser")
//    public String postAddUser(@ModelAttribute User new_user,
//                          @RequestParam("rol") Set<Role> roles) {
//    userService.save(new_user, roles);
//    return "redirect:/admin";
//}
//
//    @PostMapping("/admin/deleteUser/{id}")
//    public String postDeleteUser(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/admin/updateUser")
//    public String postUpdateUser(@ModelAttribute @Valid User user,
//                                 @RequestParam("rol") Set<Role> roles) {
//        userService.save(user, roles);
//        return "redirect:/admin";
//    }
}
