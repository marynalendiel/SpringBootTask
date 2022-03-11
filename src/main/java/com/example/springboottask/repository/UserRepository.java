package com.example.springboottask.repository;

import com.example.springboottask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u")
    List<User> findAllUsers();

    @Query("select u from User u where u.id = ?1")
    User findUserById(int userId);

    @Transactional
    @Modifying
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.email = ?3, u.city = ?4 where u.id = ?5")
    void updateFirstNameAndLastNameAndEmailAndCityById(String firstName, String lastName, String email, String city, int id);

    @Query("delete from User u where u.id = ?1")
    @Transactional
    @Modifying
    void deleteUserById(int userId);
}
