package com.example.springboottask.converter;

import com.example.springboottask.dto.UserDto;
import com.example.springboottask.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class UserDtoConverterTest {

    @Autowired
    private UserDtoConverter userDtoConverter;

    @Test
    void shouldConvertToDtoIfModelIsGiven() {
        User user = createUserModel();
        UserDto actualUserDto = userDtoConverter.toDto(user);
        UserDto expectedUserDto = createUserDto();

        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToDto() {
        UserDto actualUserDto = userDtoConverter.toDto((User) null);

        assertNull(actualUserDto);
    }

    @Test
    void shouldConvertToDtoListIfModelListIsGiven() {
        List<User> users = Collections.singletonList(createUserModel());
        List<UserDto> actualUserDtoList = userDtoConverter.toDto(users);
        List<UserDto> expectedUserDtoList = Collections.singletonList(createUserDto());

        assertEquals(expectedUserDtoList, actualUserDtoList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToDtoList() {
        List<UserDto> actualUserDtoList = userDtoConverter.toDto((List<User>) null);

        assertTrue(actualUserDtoList.isEmpty());
    }

    @Test
    void shouldConvertToModelIfDtoIsGiven() {
        UserDto userDto = createUserDto();
        User actualUser = userDtoConverter.toModel(userDto);
        User expectedUser = createUserModel();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        User actualUserDto = userDtoConverter.toModel((UserDto) null);

        assertNull(actualUserDto);
    }

    @Test
    void shouldConvertToModelListIfDtoListIsGiven() {
        List<UserDto> userDtoList = Collections.singletonList(createUserDto());
        List<User> actualUserList = userDtoConverter.toModel(userDtoList);
        List<User> expectedUserList = Collections.singletonList(createUserModel());

        assertEquals(expectedUserList, actualUserList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToModelList() {
        List<User> actualUserDtoList = userDtoConverter.toModel((List<UserDto>) null);

        assertTrue(actualUserDtoList.isEmpty());
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

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(2L);
        userDto.setFirstName("Sara");
        userDto.setLastName("Collins");
        userDto.setEmail("sara@mail.com");
        userDto.setCity("London");

        return userDto;
    }
}