package com.project.scheduler.web;

import com.project.scheduler.entity.Date;
import com.project.scheduler.entity.Event;
import com.project.scheduler.services.DateService;
import com.project.scheduler.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class DateController {

    private final DateService service;
    private final EventService eventService;

    public DateController(DateService service, EventService eventService) {
        this.service = service;
        this.eventService = eventService;
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

    @RequestMapping(value = "/remove-date/{dateId}", method = RequestMethod.GET)
    public String removeDate(@PathVariable long dateId) {
        service.delete(dateId);
        return "redirect:/events";
    }
}
