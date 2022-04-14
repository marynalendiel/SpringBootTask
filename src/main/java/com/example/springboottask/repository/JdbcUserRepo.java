package com.example.springboottask.repository;

import com.example.springboottask.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jdbc")
public class JdbcUserRepo implements UserRepo {

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findById(Long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long userId) {
        throw new UnsupportedOperationException();
    }
}
