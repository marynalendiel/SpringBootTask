package com.example.springboottask.service;

import com.example.springboottask.entity.User;
import com.example.springboottask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateFirstNameAndLastNameAndEmailAndCityById(user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getCity(), user.getId());
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUserById(userId);
    }
}
