package com.example.springboottask.converter;

import com.example.springboottask.dto.UserDto;
import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserEntityConverter.class)
class UserEntityConverterTest {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserEntityConverter userEntityConverter;

    @Test
    void shouldConvertToModelIfEntityIsGiven() {
        UserEntity userEntity = createUserEntity();
        User actualUser = userEntityConverter.convertToModel(userEntity);
        User expectedUser = createUserModel();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        UserEntity userEntity = null;
        User actualUser = userEntityConverter.convertToModel(userEntity);

        assertNull(actualUser);
    }

    @Test
    void shouldConvertToEntityIfModelIsGiven() {
        User user = createUserModel();
        UserEntity actualUserEntity = userEntityConverter.convertToEntity(user);
        UserEntity expectedUserEntity = createUserEntity();

        assertEquals(expectedUserEntity, actualUserEntity);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToEntity() {
        User user = null;
        UserEntity actualUserEntity = userEntityConverter.convertToEntity(user);

        assertNull(actualUserEntity);
    }

    private User createUserModel() {
        User user = new User();
        user.setId(2L);
        user.setFirstName("Sara");
        user.setLastName("Collins");
        user.setEmail("sara@mail.com");
        user.setCity("London");
        return user;
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2L);
        userEntity.setFirstName("Sara");
        userEntity.setLastName("Collins");
        userEntity.setEmail("sara@mail.com");
        userEntity.setCity("London");
        return userEntity;
    }
}