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

    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();

        if (user != null) {
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setCity(user.getCity());
            userDto.setOrders(user.getOrders().stream()
                    .map(order -> orderDtoConverter.convertToDto(order))
                    .collect(Collectors.toList()));

            return userDto;
        }
        return null;
    }

    public List<UserDto> convertToDtoList(List<User> users) {
        return ListUtils.emptyIfNull(users).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public User convertToModel(UserDto userDto) {
        User user = new User();

        if (userDto != null) {
            user.setId(userDto.getId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setCity(userDto.getCity());
            user.setOrders(userDto.getOrders().stream()
                    .map(orderDto -> orderDtoConverter.convertToModel(orderDto))
                    .collect(Collectors.toList()));

            return user;
        }
        return null;
    }

    public List<User> convertToModelList(List<UserDto> userDtoList) {
        return ListUtils.emptyIfNull(userDtoList).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }
}