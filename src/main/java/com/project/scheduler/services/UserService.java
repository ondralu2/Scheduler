package com.project.scheduler.services;

import com.project.scheduler.repository.UserRepository;
import com.project.scheduler.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<User> findAll(){
        return repository.findAll();
    }

    public void save(User user) {
        repository.save(user);
    }
}
