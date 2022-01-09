package com.project.scheduler.web;

import com.project.scheduler.entity.Event;
import com.project.scheduler.entity.User;
import com.project.scheduler.services.EventService;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    private final UserService service;
    private final EventService eventService;

    public UserController(UserService service, EventService eventService) {
        this.service = service;
        this.eventService = eventService;
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

    @RequestMapping("/add-user/{eventId}")
    public String addUser(Model model, @PathVariable long eventId){
        return "add-user";
    }

    @PostMapping("/add-user/{eventId}")
    public String addUserSubmit(@RequestParam String username, Model model, @PathVariable long eventId) {
        Event e = eventService.findById(eventId).get();
        User u = service.findByUsername(username);
        if (u == null)
            return "redirect:/add-user/" + eventId + "?error";
        Set<User> users = e.getUsers();
        users.add(u);
        e.setUsers(users);
        eventService.save(e);
        return "redirect:/add-user/" + eventId + "?added=" + service.findByUsername(username).getUsername();
    }

    @RequestMapping(value = "/remove-user/{eventId}/{userId}", method = RequestMethod.GET)
    public String removeUser(@PathVariable long eventId, @PathVariable long userId) {
        Event e =  eventService.findById(eventId).get();
        Set<User> users = e.getUsers();
        users.remove(service.findById(userId).get());
        e.setUsers(users);
        eventService.save(e);
        return "redirect:/events";
    }
}
