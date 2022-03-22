package com.example.springboottask.service;

import com.example.springboottask.entity.User;
import com.example.springboottask.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getUsers() {
        List<User> usersFromMock = new ArrayList<>();
        doReturn(usersFromMock).when(userRepository).findAll();

        List<User> users = userService.getUsers();
        assertEquals(users,usersFromMock);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setFirstName("Sara");
        user.setLastName("Collins");
        user.setEmail("sara@mail.com");
        user.setCity("London");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userRepository.save(user);
        assertThat(savedUser.getFirstName()).isNotNull();
    }
    
    @Test
    void getUser() {
        User user = new User();
        user.setId(12L);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        User found = userService.getUser(user.getId());
        assertThat(found.getId())
                .isEqualTo(user.getId());
    }

    @Test
    void deleteUser() {
        Long userId = 12L;

        // create the instance we will test and give it our mock
        userService.deleteUser(12L);

        // check that the expected methods were called
        verify(userRepository).deleteById(userId);
    }
}