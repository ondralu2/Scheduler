package com.project.scheduler.web;

import com.project.scheduler.entity.User;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
        User s = new User();
        s.setFirstName("Lukáš");
        s.setLastName("Ondráček");
        service.save(s);
    }

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable long id) {
        // get User from DB (via application logic) and return it in JSON
        return service.findById(id);
    }
}
