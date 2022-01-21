package com.project.scheduler.web;

import com.project.scheduler.entity.User;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getUsers() {
        return service.findAll();
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome(Model model, Principal loggedInUser){
        model.addAttribute("user", service.findByUsername(loggedInUser.getName()));
        return "welcome";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        service.save(user);
        return "registered";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal loggedInUser){
        model.addAttribute("user", service.findByUsername(loggedInUser.getName()));
        return "profile";
    }

    @RequestMapping("/edit-user")
    public String editUser(Model model, Principal loggedInUser){
        model.addAttribute("user", service.findByUsername(loggedInUser.getName()));
        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String editUserSubmit(@ModelAttribute User user) {
        service.save(user);
        return "redirect:/edit-user?edited=1";
    }

    @RequestMapping("/delete-user")
    public String deleteUser(Model model, Principal loggedInUser){
        model.addAttribute("user", service.findByUsername(loggedInUser.getName()));
        return "delete-user";
    }

    @GetMapping("/delete-user-confirm")
    public String deleteUser(Principal loggedInUser) {
        service.delete(service.findByUsername(loggedInUser.getName()).getId());
        return "redirect:/logout";
    }
}
