package com.project.scheduler.web;

import com.project.scheduler.entity.User;
import com.project.scheduler.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
        User s = new User();
        s.setFirstName("Lukáš");
        s.setLastName("Ondráček");
        service.save(s);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable long id) {
        // get User from DB (via application logic) and return it in JSON
        return service.findById(id);
    }
}
