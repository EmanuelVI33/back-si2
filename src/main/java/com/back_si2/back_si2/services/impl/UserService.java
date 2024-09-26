package com.back_si2.back_si2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.back_si2.back_si2.entities.User;
import com.back_si2.back_si2.persistence.IUserDao;
import com.back_si2.back_si2.services.IUserService;

@Service
public class UserService implements IUserService {
    private IUserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username).orElseThrow();
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

}
