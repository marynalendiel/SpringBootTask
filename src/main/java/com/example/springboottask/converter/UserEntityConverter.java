package com.example.springboottask.converter;

import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {

    @Autowired
    private ModelMapper modelMapper;

    public User convertToModel(UserEntity userEntity) {
        return userEntity != null ? modelMapper.map(userEntity, User.class) : null;
    }

    public UserEntity convertToEntity(User user) {
        return user != null ? modelMapper.map(user, UserEntity.class) : null;
    }
}
