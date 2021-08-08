package com.scheduler;

import com.scheduler.model.User;
import com.scheduler.model.Event;
import com.scheduler.repository.UserRepository;
import com.scheduler.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchedulerController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SchedulerController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String first, @RequestParam String last) {
        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        userRepository.save(user);
        return "Added new user to repo!";
    }

    @GetMapping("/list")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userRepository.findUserById(id);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/addEvent")
    public String addEvent(@RequestParam String name, @RequestParam String description) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        eventRepository.save(event);
        return "Added new event to repo!";
    }
}
