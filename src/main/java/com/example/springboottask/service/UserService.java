package com.example.springboottask.service;

import com.example.springboottask.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User user);

    User updateUser(User user);

    User getUser(Long userId);

    void deleteUser(Long userId);
}
