package com.project.scheduler.web;

import com.project.scheduler.entity.Event;
import com.project.scheduler.entity.User;
import com.project.scheduler.services.EventService;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @RequestMapping("/add-user/{eventId}")
    public String addUser(@PathVariable long eventId){
        return "add-user";
    }

    @PostMapping("/add-user/{eventId}")
    public String addUserSubmit(@RequestParam String username, @PathVariable long eventId) {
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

    @GetMapping("/remove-user/{eventId}/{userId}")
    public String removeUser(@PathVariable long eventId, @PathVariable long userId) {
        Event e =  eventService.findById(eventId).get();
        Set<User> users = e.getUsers();
        users.remove(service.findById(userId).get());
        e.setUsers(users);
        eventService.save(e);
        return "redirect:/events";
    }
}
