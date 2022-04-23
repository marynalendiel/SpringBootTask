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
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.SpyBean;

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
//@MockitoSettings(strictness = Strictness.LENIENT) - read
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @SpyBean
    UserEntityConverter userEntityConverter;

    @SpyBean
    ModelMapper modelMapper;

    //naming
    @Test
    void getUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(createUser());

        doReturn(expectedUsers).when(userRepository).findAll();

        List<User> actualUsers = userService.getUsers();
        assertEquals(expectedUsers,actualUsers);
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("Sara");
        user.setLastName("Collins");
        user.setEmail("sara@mail.com");
        user.setCity("London");

        return user;
    }

    @Test
    void saveUser() {
        User user = createUser();
        UserEntity userEntity = userEntityConverter.convertToEntity(user);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        assertThat(savedUser.getFirstName()).isNotNull();
        assertThat(savedUser.getLastName()).isNotNull();
        assertThat(savedUser.getEmail()).isNotNull();
        assertThat(savedUser.getCity()).isNotNull();
    }
    
    @Test
    void getUser() {
        UserEntity user = new UserEntity();
        user.setId(12L);

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        User found = userService.getUser(user.getId());
        assertThat(found.getId())
                .isEqualTo(user.getId());
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