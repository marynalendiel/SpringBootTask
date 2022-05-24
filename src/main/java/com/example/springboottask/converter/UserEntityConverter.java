package com.example.springboottask.converter;

import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityConverter {

    @Autowired
    private OrderEntityConverter orderEntityConverter;

    public User toModel(UserEntity userEntity) {
        User user = new User();

        if (userEntity != null) {
            user.setId(userEntity.getId());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setEmail(userEntity.getEmail());
            user.setCity(userEntity.getCity());
            user.setOrders(userEntity.getOrders().stream()
                    .map(orderEntity -> orderEntityConverter.toModel(orderEntity))
                    .collect(Collectors.toList()));

            return user;
        }
        return null;
    }

    public List<User> toModel(List<UserEntity> userEntityList) {
        return ListUtils.emptyIfNull(userEntityList).stream()
                .map(this::toModel)
                .collect(Collectors.toList());

    }

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        if (user != null) {
            userEntity.setId(user.getId());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmail(user.getEmail());
            userEntity.setCity(user.getCity());
            userEntity.setOrders(user.getOrders().stream()
                    .map(order -> orderEntityConverter.toEntity(order))
                    .collect(Collectors.toList()));

            return userEntity;
        }
        return null;
    }

    public List<UserEntity> toEntity(List<User> users) {
        return ListUtils.emptyIfNull(users).stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

    }
}
