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
    public String addUser(@RequestParam String first, @RequestParam String last, @RequestParam String password,
                          @RequestParam String email, @RequestParam String phone) {
        if (first == "" || last == "" || password == "" || email == "" || phone == "")
            return "Vyplňte prosím všechna pole.";
        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);
        return "Registrace proběhla úspěšně.";
    }

    @GetMapping("/list")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userRepository.findUserById(id);
    }

    @PostMapping("/addEvent")
    public String addEvent(@RequestParam String name, @RequestParam String description) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        eventRepository.save(event);
        return "Nová událost vytvořena, přidejte termíny a místa";
    }
}
