package com.example.springboottask.service;

import com.example.springboottask.controller.EntityResultNotFoundException;
import com.example.springboottask.converter.UserEntityConverter;
import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import com.example.springboottask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserEntityConverter userEntityConverter;

    public UserServiceImpl(UserRepository userRepository, UserEntityConverter userEntityConverter) {
        this.userRepository = userRepository;
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntityConverter.toModel(userEntities);
    }

    @Snapshot
    @Override
    public User getUser(Long userId) {
//        return userRepository.findById(userId).orElseThrow(
//                () -> new EntityResultNotFoundException("User not found.."));

//        .orElseThrow(
//                new Supplier<EntityResultNotFoundException>() {
//                    @Override
//                    public EntityResultNotFoundException get() {
//                        return new EntityResultNotFoundException("User not found..");
//                    }
//                }
//        );
        return userRepository.findById(userId)
                .map(userEntityConverter::toModel)
                .orElseThrow(() -> new EntityResultNotFoundException("User not found"));
    }

    @Snapshot
    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityConverter.toEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = userEntityConverter.toEntity(user);

        User userFromDb = userRepository.findById(userEntity.getId())
                .map(userEntityConverter::toModel)
                .orElseThrow(() -> new EntityResultNotFoundException("must updated user not found"));

        userFromDb.setFirstName(userEntity.getFirstName());
        userFromDb.setLastName(userEntity.getLastName());
        userFromDb.setCity(userEntity.getCity());
        userRepository.save(userEntityConverter.toEntity(userFromDb));

        return userFromDb;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
