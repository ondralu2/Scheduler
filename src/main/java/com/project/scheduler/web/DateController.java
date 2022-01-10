package com.project.scheduler.web;

import com.project.scheduler.entity.Date;
import com.project.scheduler.entity.Event;
import com.project.scheduler.entity.User;
import com.project.scheduler.services.DateService;
import com.project.scheduler.services.EventService;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class DateController {

    private final DateService service;
    private final EventService eventService;
    private final UserService userService;

    public DateController(DateService service, EventService eventService, UserService userService) {
        this.service = service;
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping("/add-date/{eventId}")
    public String addDate(Model model, @PathVariable long eventId){
        model.addAttribute("date", new Date());
        return "add-date";
    }

    @PostMapping("/add-date/{eventId}")
    public String addDateSubmit(@ModelAttribute Date date, @PathVariable long eventId) {
        service.save(date);
        Event e = eventService.findById(eventId).get();
        Set<Date> dates = e.getDates();
        dates.add(date);
        e.setDates(dates);
        eventService.save(e);
        return "redirect:/add-date/" + eventId + "?added=" + date.getName();
    }

    @RequestMapping("/edit-date/{id}")
    public String editDate(@PathVariable long id, Model model){
        model.addAttribute("date", service.findById(id).get());
        return "edit-date";
    }

    @PostMapping("/edit-date/{id}")
    public String editDateSubmit(@PathVariable long id, @ModelAttribute Date date) {
        service.save(date);
        return "redirect:/edit-date/" + id + "?edited=1";
    }

    @RequestMapping(value = "/remove-date/{dateId}", method = RequestMethod.GET)
    public String removeDate(@PathVariable long dateId) {
        service.delete(dateId);
        return "redirect:/events";
    }

    @RequestMapping(value = "/enrol-date/{dateId}/{userId}", method = RequestMethod.GET)
    public String enrolDate(@PathVariable long dateId, @PathVariable long userId) {
        Date d = service.findById(dateId).get();
        Set<User> users = d.getUsers();
        users.add(userService.findById(userId).get());
        d.setUsers(users);
        service.save(d);
        return "redirect:/events";
    }

    @RequestMapping(value = "/leave-date/{dateId}/{userId}", method = RequestMethod.GET)
    public String leaveDate(@PathVariable long dateId, @PathVariable long userId) {
        Date d = service.findById(dateId).get();
        Set<User> users = d.getUsers();
        users.remove(userService.findById(userId).get());
        d.setUsers(users);
        service.save(d);
        return "redirect:/events";
    }
}
