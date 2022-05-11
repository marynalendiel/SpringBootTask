package com.example.springboottask.service;

import com.example.springboottask.converter.UserEntityConverter;
import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import com.example.springboottask.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserEntityConverter userEntityConverter;

    //naming
    @Test
    void getUsers() {
        List<UserEntity> expectedUserEntities = new ArrayList<>();
        expectedUserEntities.add(createUserEntity());
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(createUserModel());

        doReturn(expectedUserEntities).when(userRepository).findAll();
        when(userEntityConverter.convertToModel(expectedUserEntities.get(0))).thenReturn(expectedUsers.get(0));

        List<User> actualUsers = userService.getUsers();
        assertEquals(expectedUsers,actualUsers);
    }

    private User createUserModel() {
        User user = new User();
        user.setFirstName("Sara");
        user.setLastName("Collins");
        user.setEmail("sara@mail.com");
        user.setCity("London");

        return user;
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Sara");
        userEntity.setLastName("Collins");
        userEntity.setEmail("sara@mail.com");
        userEntity.setCity("London");

        return userEntity;
    }

    @Test
    void saveUser() {
        UserEntity userEntity = createUserEntity();

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        assertThat(savedUser.getFirstName()).isNotNull();
        assertThat(savedUser.getLastName()).isNotNull();
        assertThat(savedUser.getEmail()).isNotNull();
        assertThat(savedUser.getCity()).isNotNull();
    }
    
    @Test
    void getUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(12L);
        User user = new User();
        user.setId(12L);

        when(userRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        when(userEntityConverter.convertToModel(any(UserEntity.class))).thenReturn(user);

        User found = userService.getUser(userEntity.getId());
        assertThat(found.getId())
                .isEqualTo(userEntity.getId());
        // + verify
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
//naming + verify + check all
//read about first + kiss