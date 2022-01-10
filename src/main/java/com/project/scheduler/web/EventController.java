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

    @RequestMapping(value = "/delete-event/{id}")
    public String removeEvent(@PathVariable long id, Model model) {
        model.addAttribute("event", service.findById(id).get());
        return "delete-event";
    }

    @RequestMapping(value = "/delete-event-confirm/{id}", method = RequestMethod.GET)
    public String removeEventConfirm(@PathVariable long id) {
        service.delete(id);
        return "redirect:/events";
    }
}
