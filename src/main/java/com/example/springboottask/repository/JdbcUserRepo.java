package com.example.springboottask.repository;

import com.example.springboottask.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jdbc")
public class JdbcUserRepo implements UserRepo {

    @Override
    public List<UserEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(UserEntity user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findById(Long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long userId) {
        throw new UnsupportedOperationException();
    }
}
