package com.example.springboottask.controller;

import com.example.springboottask.converter.OrderDtoConverter;
import com.example.springboottask.converter.UserDtoConverter;
import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.dto.UserDto;
import com.example.springboottask.model.Order;
import com.example.springboottask.model.User;
import com.example.springboottask.service.OrderService;
import com.example.springboottask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final OrderService orderService;

    private final UserDtoConverter userDtoConverter;

    private final OrderDtoConverter orderDtoConverter;

    @GetMapping
    public List<UserDto> getUsers() {
        List<User> users = userService.getUsers();
        return userDtoConverter.toDto(users);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {

        User user = userService.getUser(userId);
        UserDto userDto = userDtoConverter.toDto(user);

        if (userDto == null) {
            throw new EntityResultNotFoundException("User id not found - " + userId);
        }

        return userDto;
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDtoConverter.toModel(userDto));
        return userDto;
    }

    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        User user = userDtoConverter.toModel(userDto);
        User updatedUser = userService.updateUser(user);
        return userDtoConverter.toDto(updatedUser);
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
    public List<OrderDto> getUserOrders(@PathVariable Long userId) {
        List<Order> orderList = orderService.getUserOrders(userId);

        return orderDtoConverter.toDto(orderList);
    }
}
