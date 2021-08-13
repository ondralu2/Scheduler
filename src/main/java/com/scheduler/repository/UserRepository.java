package com.scheduler.repository;

import com.scheduler.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByEmail(String email);
}
