package com.example.springboottask.controller;

import com.example.springboottask.entity.Order;
import com.example.springboottask.entity.User;
import com.example.springboottask.service.OrderService;
import com.example.springboottask.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    List<User> users = new ArrayList<>();
    User user = new User();

    @Test
    void getUsers() throws Exception {
        when(userService.getUsers()).thenReturn(users);

        final String expectedResponseContent = objectMapper.writeValueAsString(users);
        this.mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }

    @Test
    void getUser() throws Exception {
        when(userService.getUser(2L)).thenReturn(user);

        final String expectedResponseContent = objectMapper.writeValueAsString(user);
        this.mockMvc.perform(get("/api/users/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }

    @Test
    void addUser() throws Exception {
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
        when(userService.updateUser(user)).thenReturn(user);
        final String jsonUser = objectMapper.writeValueAsString(user);
        this.mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
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