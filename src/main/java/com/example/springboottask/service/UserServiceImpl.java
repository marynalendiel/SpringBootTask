package com.example.springboottask.service;

import com.example.springboottask.converter.UserEntityConverter;
import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import com.example.springboottask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return userEntities.stream()
                .map(userEntity -> userEntityConverter.convertToModel(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityConverter.convertToEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = userEntityConverter.convertToEntity(user);

        UserEntity userFromDb = userRepository.findById(userEntity.getId()).get();

        userFromDb.setFirstName(userEntity.getFirstName());
        userFromDb.setLastName(userEntity.getLastName());
        userFromDb.setCity(userEntity.getCity());
        userRepository.save(userFromDb);

        return userEntityConverter.convertToModel(userFromDb);
    }

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
        UserEntity userEntity = userRepository.findById(userId).get();
        return userEntityConverter.convertToModel(userEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
