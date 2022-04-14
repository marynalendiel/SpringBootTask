package com.example.springboottask.controller;

import com.example.springboottask.entity.Order;
import com.example.springboottask.entity.User;
import com.example.springboottask.service.OrderService;
import com.example.springboottask.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class) //slice
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUsers() throws Exception {
        User user = createUser();
        List<User> users = Collections.singletonList(user);
        when(userService.getUsers()).thenReturn(users);

        final String expectedResponseContent = objectMapper.writeValueAsString(users);
        this.mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }

    @Test
    void shouldReturnUserWhenUserIdIsGiven() throws Exception {
        //given
        User user = createUser();
        String expectedResponseContent = objectMapper.writeValueAsString(user);

        when(userService.getUser(2L)).thenReturn(user);

        //when
        this.mockMvc.perform(get("/api/users/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }

    private User createUser() {
        User user = new User();
        user.setId(2L);
        user.setFirstName("Sara");
        user.setLastName("Collins");
        user.setEmail("sara@mail.com");
        user.setCity("London");
        return user;
    }

    @Test
    void shouldCreateUserWhenUserIsGiven() throws Exception {
        User user = createUser();
        final String jsonUser = objectMapper.writeValueAsString(user);

        this.mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonUser));
    }

    @Test
    void updateUser() throws Exception {
        User user = createUser();
        when(userService.updateUser(user)).thenReturn(user);
        final String jsonUser = objectMapper.writeValueAsString(user);
        this.mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService).updateUser(user);
    }

    @Test
    void deleteUser() throws Exception {
        User user = createUser();
        when(userService.getUser(2L)).thenReturn(user);

        this.mockMvc
                .perform(delete("/api/users/{userId}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted user id - ")));
    }

    @Test
    void getUserOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        final String jsonOrders = objectMapper.writeValueAsString(orders);

        when(orderService.getUserOrders(2L)).thenReturn(orders);

        this.mockMvc.perform(get("/api/users/{userId}/orders", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOrders));
    }
}