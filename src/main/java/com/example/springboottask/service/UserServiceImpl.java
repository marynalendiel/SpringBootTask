package com.example.springboottask.service;

import com.example.springboottask.entity.User;
import com.example.springboottask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    public UserServiceImpl(@Qualifier("jpa") UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userFromDb = userRepository.findById(user.getId());
//        .get();

        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setCity(user.getCity());
        userRepository.save(userFromDb);

        return userFromDb;
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

        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
