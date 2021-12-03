package com.project.scheduler.web;

import com.project.scheduler.entity.Event;
import com.project.scheduler.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
        Event e = new Event();
        e.setName("Výnoční večírek");
        e.setDescription("Tradiční každoroční bowling.");
        //User author =
        //e.setAuthor(User.class );
        /*Set<User> users = (Set<User>) new ArrayList<User>();
        users.add(author);
        e.setUsers(users);*/
        service.save(e);
    }

    @GetMapping("/events")
    public String userEvents(Model model) {
        model.addAttribute("events", service.findAll());
        return "events";
    }

    @RequestMapping("/create-event")
    public String newEvent(Model model){
        model.addAttribute("event", new Event());
        return "create-event";
    }

    @PostMapping("/create-event")
    public String newEventSubmit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        service.save(event);
        return "created-event";
    }

}
