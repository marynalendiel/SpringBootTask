package com.example.springboottask.converter;

import com.example.springboottask.dto.UserDto;
import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import org.apache.commons.collections4.ListUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityConverter {

    @Autowired
    private OrderEntityConverter orderEntityConverter;

    public User convertToModel(UserEntity userEntity) {
        User user = new User();

        if (userEntity != null) {
            user.setId(userEntity.getId());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setEmail(userEntity.getEmail());
            user.setCity(userEntity.getCity());
            user.setOrders(userEntity.getOrders().stream()
                    .map(orderEntity -> orderEntityConverter.convertToModel(orderEntity))
                    .collect(Collectors.toList()));

            return user;
        }
        return null;
    }

    public List<User> convertToModelList(List<UserEntity> userEntityList) {
        return ListUtils.emptyIfNull(userEntityList).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());

    }

    public UserEntity convertToEntity(User user) {
        UserEntity userEntity = new UserEntity();

        if (user != null) {
            userEntity.setId(user.getId());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmail(user.getEmail());
            userEntity.setCity(user.getCity());
            userEntity.setOrders(user.getOrders().stream()
                    .map(order -> orderEntityConverter.convertToEntity(order))
                    .collect(Collectors.toList()));

            return userEntity;
        }
        return null;
    }

    public List<UserEntity> convertToEntityList(List<User> users) {
        return ListUtils.emptyIfNull(users).stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

    }
}
