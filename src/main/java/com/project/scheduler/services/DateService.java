package com.project.scheduler.services;

import com.project.scheduler.entity.Date;
import com.project.scheduler.repository.DateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DateService {

    private final DateRepository repository;

    public DateService(DateRepository repository) {
        this.repository = repository;
    }

    public Optional<Date> findById(long id) {
        return repository.findById(id);
    }

    public void save(Date date) {
        repository.save(date);
    }

    public void delete(long id) {
        repository.delete(repository.getById(id));
    }
}
