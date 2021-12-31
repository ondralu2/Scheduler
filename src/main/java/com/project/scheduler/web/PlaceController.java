package com.project.scheduler.web;

import com.project.scheduler.entity.GpsCoordinate;
import com.project.scheduler.entity.Place;
import com.project.scheduler.entity.Event;
import com.project.scheduler.services.GpsCoordinateService;
import com.project.scheduler.services.PlaceService;
import com.project.scheduler.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class PlaceController {

    private final PlaceService service;
    private final EventService eventService;
    private final GpsCoordinateService coordinateService;

    public PlaceController(PlaceService service, EventService eventService, GpsCoordinateService coordinateService) {
        this.service = service;
        this.eventService = eventService;
        this.coordinateService = coordinateService;
    }

    @RequestMapping("/add-place/{eventId}")
    public String addPlace(Model model, @PathVariable long eventId){
        model.addAttribute("place", new Place());
        model.addAttribute("coordinate", new GpsCoordinate());
        return "add-place";
    }

    @PostMapping("/add-place/{eventId}")
    public String addPlaceSubmit(@ModelAttribute Place place, @ModelAttribute GpsCoordinate coordinate, Model model, @PathVariable long eventId) {
        model.addAttribute("place", place);
        model.addAttribute("coordinate", coordinate);
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
}