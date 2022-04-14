package com.example.springboottask.repository;

import com.example.springboottask.entity.User;

import java.util.List;

public interface UserRepo {

    List<User> findAll();

    void save(User user);

    User findById(Long userId);

    void deleteById(Long userId);
}
