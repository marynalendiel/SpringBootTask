package com.example.springboottask.converter;

import com.example.springboottask.dto.UserDto;
import com.example.springboottask.model.User;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    @Autowired
    private OrderDtoConverter orderDtoConverter;

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        if (user != null) {
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setCity(user.getCity());
            userDto.setOrders(orderDtoConverter.toDto(user.getOrders()));

            return userDto;
        }
        return null;
    }

    public List<UserDto> toDto(List<User> users) {
        return ListUtils.emptyIfNull(users).stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public User toModel(UserDto userDto) {
        User user = new User();

        if (userDto != null) {
            user.setId(userDto.getId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setCity(userDto.getCity());
            user.setOrders(userDto.getOrders().stream()
                    .map(orderDto -> orderDtoConverter.toModel(orderDto))
                    .collect(Collectors.toList()));

            return user;
        }
        return null;
    }

    public List<User> toModel(List<UserDto> userDtoList) {
        return ListUtils.emptyIfNull(userDtoList).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}