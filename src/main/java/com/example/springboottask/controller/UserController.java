package com.example.springboottask.controller;

import com.example.springboottask.entity.Order;
import com.example.springboottask.entity.User;
import com.example.springboottask.service.OrderService;
import com.example.springboottask.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final OrderService orderService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {

        User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityResultNotFoundException("User id not found - " + userId);
        }

        return user;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.saveUser(user);

        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityResultNotFoundException("User id not found - " + userId);
        }
        userService.deleteUser(userId);

        return "Deleted user id - " + userId;
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }
}
