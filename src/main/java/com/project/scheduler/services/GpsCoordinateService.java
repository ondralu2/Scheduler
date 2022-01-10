package com.project.scheduler.services;

import com.project.scheduler.entity.GpsCoordinate;
import com.project.scheduler.repository.GpsCoordinateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GpsCoordinateService {

    private final GpsCoordinateRepository repository;

    public GpsCoordinateService(GpsCoordinateRepository repository) {
        this.repository = repository;
    }

    public Optional<GpsCoordinate> findById(long id) {
        return repository.findById(id);
    }

    public void save(GpsCoordinate coordinate) {
        repository.save(coordinate);
    }

    public void delete(long id) {
        repository.delete(repository.getById(id));
    }
}
