package com.example.springboottask.repository;

import com.example.springboottask.entity.UserEntity;

import java.util.List;

public interface UserRepo {

    List<UserEntity> findAll();

    void save(UserEntity user);

    UserEntity findById(Long userId);

    void deleteById(Long userId);
}
