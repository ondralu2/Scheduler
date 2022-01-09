package com.project.scheduler.services;

import com.project.scheduler.entity.Place;
import com.project.scheduler.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private final PlaceRepository repository;

    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public void save(Place place) {
        repository.save(place);
    }

    public void delete(long id) {
        repository.delete(repository.getById(id));
    }
}
