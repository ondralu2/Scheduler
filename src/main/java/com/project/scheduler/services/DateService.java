package com.project.scheduler.services;

import com.project.scheduler.entity.Date;
import com.project.scheduler.repository.DateRepository;
import org.springframework.stereotype.Service;

@Service
public class DateService {

    private final DateRepository repository;

    public DateService(DateRepository repository) {
        this.repository = repository;
    }

    public void save(Date date) {
        repository.save(date);
    }
}
