package com.example.springboottask.converter;

import com.example.springboottask.dto.UserDto;
import com.example.springboottask.model.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserDtoConverter.class)
class UserDtoConverterTest {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDtoConverter userDtoConverter;

    @Test
    void shouldConvertToDtoIfModelIsGiven() {
        User user = createUserModel();
        UserDto actualUserDto = userDtoConverter.convertToDto(user);
        UserDto expectedUserDto = createUserDto();

        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToDto() {
        User user = null;
        UserDto actualUserDto = userDtoConverter.convertToDto(user);

        assertNull(actualUserDto);
    }

    @Test
    void shouldConvertToModelIfDtoIsGiven() {
        UserDto userDto = createUserDto();
        User actualUser = userDtoConverter.convertToModel(userDto);
        User expectedUser = createUserModel();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        UserDto userDto = null;
        User actualUserDto = userDtoConverter.convertToModel(userDto);

        assertNull(actualUserDto);
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