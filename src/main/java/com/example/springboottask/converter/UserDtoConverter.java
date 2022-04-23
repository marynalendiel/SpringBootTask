package com.example.springboottask.converter;

import com.example.springboottask.dto.UserDto;
import com.example.springboottask.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToDto(User user) {
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }

    public User convertToModel(UserDto userDto) {
        return userDto != null ? modelMapper.map(userDto, User.class) : null;
    }
}
