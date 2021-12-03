package com.project.scheduler.repository;

import com.project.scheduler.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    //Collection<Event> findByUserId(long id);
}
