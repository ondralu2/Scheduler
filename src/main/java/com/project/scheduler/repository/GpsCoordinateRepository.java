package com.project.scheduler.repository;

import com.project.scheduler.entity.GpsCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpsCoordinateRepository extends JpaRepository<GpsCoordinate, Long> {
}
