package com.project.scheduler.services;

import com.project.scheduler.repository.UserRepository;
import com.project.scheduler.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    public void save(User s) {
        repository.save(s);
    }
}
