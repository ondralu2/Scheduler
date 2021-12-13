package com.project.scheduler.web;

import com.project.scheduler.entity.Event;
import com.project.scheduler.entity.User;
import com.project.scheduler.services.EventService;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class EventController {

    private final EventService service;
    private final UserService userService;

    public EventController(EventService service, UserService userService) {
        this.service = service;
        this.userService = userService;
        Event e = new Event();
        e.setName("Vánoční večírek");
        e.setDescription("Tradiční každoroční bowling.");
        User u = new User();
        u.setUsername("User1");
        userService.save(u);
        e.setAuthor(u);
        Set<User> users = new HashSet<>();
        users.add(u);
        //users.add(userService.findByUsername("User1"));
        e.setUsers(users);
        service.save(e);
    }

    @GetMapping("/events")
    public String userEvents(Model model, Principal loggedInUser) {
        User user = userService.findByUsername(loggedInUser.getName());
        model.addAttribute("events", user.getEvents());
        return "events";
    }

    @RequestMapping("/create-event")
    public String newEvent(Model model){
        model.addAttribute("event", new Event());
        return "create-event";
    }

    @PostMapping("/create-event")
    public String newEventSubmit(@ModelAttribute Event event, Model model, Principal loggedInUser) {
        model.addAttribute("event", event);
        User user = userService.findByUsername(loggedInUser.getName());
        event.setAuthor(user);
        Set<User> users = new HashSet<>();
        users.add(user);
        event.setUsers(users);
        service.save(event);
        return "created-event";
    }

}
