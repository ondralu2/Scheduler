package com.project.scheduler.web;

import com.project.scheduler.entity.Event;
import com.project.scheduler.entity.User;
import com.project.scheduler.services.EventService;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    }

    @GetMapping("/events")
    public String userEvents(Model model, Principal loggedInUser) {
        User user = userService.findByUsername(loggedInUser.getName());
        model.addAttribute("loggedInUser", user);
        model.addAttribute("events", user.getEvents());
        model.addAttribute("counter", new Counter());
        return "events";
    }

    @RequestMapping("/create-event")
    public String newEvent(Model model){
        model.addAttribute("event", new Event());
        return "create-event";
    }

    @PostMapping("/create-event")
    public String newEventSubmit(@ModelAttribute Event event, Principal loggedInUser) {
        User user = userService.findByUsername(loggedInUser.getName());
        event.setAuthor(user);
        Set<User> users = new HashSet<>();
        users.add(user);
        event.setUsers(users);
        service.save(event);
        return "created-event";
    }

    @RequestMapping("/edit-event/{id}")
    public String editEvent(@PathVariable long id, Model model){
        model.addAttribute("event", service.findById(id).get());
        return "edit-event";
    }

    @PostMapping("/edit-event/{id}")
    public String editEventSubmit(@PathVariable long id, @ModelAttribute Event event) {
        service.save(event);
        return "redirect:/edit-event/" + id + "?edited=1";
    }

    @RequestMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable long id, Model model) {
        model.addAttribute("event", service.findById(id).get());
        return "delete-event";
    }

    @GetMapping("/delete-event-confirm/{id}")
    public String deleteEventConfirm(@PathVariable long id) {
        service.delete(id);
        return "events";
    }


    @RequestMapping("/add-user/{eventId}")
    public String addUser(@PathVariable long eventId){
        return "add-user";
    }

    @PostMapping("/add-user/{eventId}")
    public String addUserSubmit(@RequestParam String username, @PathVariable long eventId) {
        Event e = service.findById(eventId).get();
        User u = userService.findByUsername(username);
        if (u == null)
            return "redirect:/add-user/" + eventId + "?error";
        Set<User> users = e.getUsers();
        users.add(u);
        e.setUsers(users);
        service.save(e);
        return "redirect:/add-user/" + eventId + "?added=" + userService.findByUsername(username).getUsername();
    }

    @GetMapping("/remove-user/{eventId}/{userId}")
    public String removeUser(@PathVariable long eventId, @PathVariable long userId) {
        Event e =  service.findById(eventId).get();
        Set<User> users = e.getUsers();
        users.remove(userService.findById(userId).get());
        e.setUsers(users);
        service.save(e);
        return "redirect:/events";
    }
}
