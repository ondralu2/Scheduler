package com.project.scheduler.services;

import com.project.scheduler.entity.GpsCoordinate;
import com.project.scheduler.repository.GpsCoordinateRepository;
import org.springframework.stereotype.Service;

@Service
public class GpsCoordinateService {

    private final GpsCoordinateRepository repository;

    public GpsCoordinateService(GpsCoordinateRepository repository) {
        this.repository = repository;
    }

    public void save(GpsCoordinate coordinate) {
        repository.save(coordinate);
    }
}
