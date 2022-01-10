package com.project.scheduler.web;

import com.project.scheduler.entity.*;
import com.project.scheduler.services.GpsCoordinateService;
import com.project.scheduler.services.PlaceService;
import com.project.scheduler.services.EventService;
import com.project.scheduler.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class PlaceController {

    private final PlaceService service;
    private final GpsCoordinateService coordinateService;
    private final EventService eventService;
    private final UserService userService;

    public PlaceController(PlaceService service, EventService eventService, GpsCoordinateService coordinateService, UserService userService) {
        this.service = service;
        this.eventService = eventService;
        this.coordinateService = coordinateService;
        this.userService = userService;
    }

    @RequestMapping("/add-place/{eventId}")
    public String addPlace(Model model, @PathVariable long eventId){
        model.addAttribute("place", new Place());
        model.addAttribute("coordinate", new GpsCoordinate());
        return "add-place";
    }

    @PostMapping("/add-place/{eventId}")
    public String addPlaceSubmit(@ModelAttribute Place place, @ModelAttribute GpsCoordinate coordinate, @PathVariable long eventId) {
        coordinateService.save(coordinate);
        place.setLocation(coordinate);
        service.save(place);
        Event e = eventService.findById(eventId).get();
        Set<Place> places = e.getPlaces();
        places.add(place);
        e.setPlaces(places);
        eventService.save(e);
        return "redirect:/add-place/" + eventId + "?added=" + place.getName();
    }

    @RequestMapping(value = "/remove-place/{placeId}", method = RequestMethod.GET)
    public String removePlace(@PathVariable long placeId) {
        service.delete(placeId);
        return "redirect:/events";
    }

    @RequestMapping(value = "/enrol-place/{placeId}/{userId}", method = RequestMethod.GET)
    public String enrolDate(@PathVariable long placeId, @PathVariable long userId) {
        Place p = service.findById(placeId).get();
        Set<User> users = p.getUsers();
        users.add(userService.findById(userId).get());
        p.setUsers(users);
        service.save(p);
        return "redirect:/events";
    }

    @RequestMapping(value = "/leave-place/{placeId}/{userId}", method = RequestMethod.GET)
    public String leaveDate(@PathVariable long placeId, @PathVariable long userId) {
        Place p = service.findById(placeId).get();
        Set<User> users = p.getUsers();
        users.remove(userService.findById(userId).get());
        p.setUsers(users);
        service.save(p);
        return "redirect:/events";
    }
}
