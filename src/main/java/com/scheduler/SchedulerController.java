package com.scheduler;

import com.scheduler.model.User;
import com.scheduler.model.Event;
import com.scheduler.repository.UserRepository;
import com.scheduler.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SchedulerController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SchedulerController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        return "newUser";
    }

    // /hello?name=jmeno
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name, Model model) {

        model.addAttribute("message", name);

        return "main";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String first, @RequestParam String last, @RequestParam String password,
                          @RequestParam String email, @RequestParam String phone, Model model) {
        if (first.equals("") || last.equals("") || password.equals("") || email.equals("") || phone.equals("")) {
            model.addAttribute("message", "Vyplňte prosím všechna pole.");
            return "message";
        }

        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);
        model.addAttribute("message", "Registrace proběhla úspěšně.");
        return "message";
    }

    @GetMapping("/list")
    public String getUsers(Model model) {
        model.addAttribute("message", userRepository.findAll());
        return "table";
    }

    @GetMapping("/newEvent")
    public String newEvent(Model model) {
        return "newEvent";
    }

    @PostMapping("/addEvent")
    public String addEvent(@RequestParam String name, @RequestParam String description, Model model) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        eventRepository.save(event);
        model.addAttribute("message", "Nová událost vytvořena, přidejte termíny a místa.");
        return "message";
    }

    @GetMapping("/logIn")
    public String logIn(Model model) {
        return "logIn";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            model.addAttribute("message", "Přihlášení se nezdařilo - zadaný e-mail je špatně.");
        }
        else if (user.getPassword().equals(password))
            model.addAttribute("message", "Přihlášení proběhlo úspěšně.");
        else
            model.addAttribute("message", "Přihlášení se nezdařilo - zadané heslo je špatně.");
        return "message";
    }

    @GetMapping("/logOut")
    public String logOut(Model model) {
        model.addAttribute("message", "Uživatel odhlášen.");
        return "message";
    }
}
