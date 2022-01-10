package com.project.scheduler.services;

import com.project.scheduler.entity.Event;
import com.project.scheduler.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Optional<Event> findById(long id) {
        return repository.findById(id);
    }

    public List<Event> findAll(){
        return repository.findAll();
    }

    public void save(Event event) {
        repository.save(event);
    }

    public void delete(long id) {
        repository.delete(repository.getById(id));
    }
}
