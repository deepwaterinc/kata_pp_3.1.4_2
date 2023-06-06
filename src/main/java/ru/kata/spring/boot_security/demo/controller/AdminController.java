package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/admin")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", userService.findAllRoles());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user1", user);
        return "adminPanel";
    }

    @GetMapping("/admin/user/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showById(id));
        return "userPage";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("userNew", new User());
        model.addAttribute("allRoles", userService.findAllRoles());
        return "adminPanel";
    }

    @PostMapping("/admin/user")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showById(id));
        model.addAttribute("allRoles", userService.findAllRoles());
        return "adminPanel";
    }

    @PatchMapping("/admin/user/{id}")
    public String update(User user, @PathVariable("id") Long id, Model model) {
        userService.updateUserById(id, user, model);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/user/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }


}
