package com.example.springboottask.converter;

import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class UserEntityConverterTest {

    @Autowired
    UserEntityConverter userEntityConverter;

    @Test
    void shouldConvertToModelIfEntityIsGiven() {
        UserEntity userEntity = createUserEntity();
        User actualUser = userEntityConverter.toModel(userEntity);
        User expectedUser = createUserModel();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        User actualUser = userEntityConverter.toModel((UserEntity) null);

        assertNull(actualUser);
    }

    @Test
    void shouldConvertToModelListIfEntityListIsGiven() {
        List<UserEntity> userEntityList = Collections.singletonList(createUserEntity());
        List<User> actualUserList = userEntityConverter.toModel(userEntityList);
        List<User> expectedUserList = Collections.singletonList(createUserModel());

        assertEquals(expectedUserList, actualUserList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToModelList() {
        List<User> actualUserList = userEntityConverter.toModel((List<UserEntity>) null);

        assertTrue(actualUserList.isEmpty());
    }

    @Test
    void shouldConvertToEntityIfModelIsGiven() {
        User user = createUserModel();
        UserEntity actualUserEntity = userEntityConverter.toEntity(user);
        UserEntity expectedUserEntity = createUserEntity();

        assertEquals(expectedUserEntity, actualUserEntity);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToEntity() {
        UserEntity actualUserEntity = userEntityConverter.toEntity((User) null);

        assertNull(actualUserEntity);
    }

    @Test
    void shouldConvertToEntityListIfModelListIsGiven() {
        List<User> users = Collections.singletonList(createUserModel());
        List<UserEntity> actualUserEntityList = userEntityConverter.toEntity(users);
        List<UserEntity> expectedUserEntityList = Collections.singletonList(createUserEntity());

        assertEquals(expectedUserEntityList, actualUserEntityList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToEntityList() {
        List<UserEntity> actualUserEntityList = userEntityConverter.toEntity((List<User>) null);

        assertTrue(actualUserEntityList.isEmpty());
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