package com.example.springboottask.service;

import com.example.springboottask.controller.EntityResultNotFoundException;
import com.example.springboottask.entity.User;
import com.example.springboottask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        User userFromDb = userRepository.getById(user.getId());

        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setCity(user.getCity());
        userRepository.save(userFromDb);

        return userFromDb;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityResultNotFoundException("User not found.."));

//        return userRepository.findById(userId).orElseThrow(
//                new Supplier<EntityResultNotFoundException>() {
//                    @Override
//                    public EntityResultNotFoundException get() {
//                        return new EntityResultNotFoundException("User not found..");
//                    }
//                }
//        );
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
