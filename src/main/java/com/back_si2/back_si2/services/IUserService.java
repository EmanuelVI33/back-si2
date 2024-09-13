package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.User;

public interface IUserService {
    User findById(Long id);

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);
}
