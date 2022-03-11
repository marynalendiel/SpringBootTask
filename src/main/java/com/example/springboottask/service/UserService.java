package com.example.springboottask.service;

import com.example.springboottask.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUser(int userId);

    void deleteUser(int userId);
}
