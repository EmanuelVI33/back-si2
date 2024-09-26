package com.back_si2.back_si2.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.back_si2.back_si2.entities.User;

public interface IUserDao extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
