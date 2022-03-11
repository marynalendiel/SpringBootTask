package com.example.springboottask.controller;

import com.example.springboottask.entity.Order;
import com.example.springboottask.entity.User;
import com.example.springboottask.service.OrderService;
import com.example.springboottask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = {"/users"})
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {

        User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityResultNotFoundException("User id not found - " + userId);
        }

        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        userService.saveUser(user);

        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        userService.updateUser(user);

        return user;

    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityResultNotFoundException("User id not found - " + userId);
        }
        userService.deleteUser(userId);

        return "Deleted user id - " + userId;
    }

    @GetMapping("/users/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable int userId) {
        return orderService.getUserOrders(userId);
    }

}
