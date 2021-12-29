package com.project.scheduler.web;

import com.project.scheduler.entity.User;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
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

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable long id) {
        // get User from DB (via application logic) and return it in JSON
        return service.findById(id);
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }
}
