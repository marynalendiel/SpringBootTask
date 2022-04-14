package com.example.springboottask.service;

import com.example.springboottask.entity.Main;
import com.example.springboottask.entity.Order2;
import com.example.springboottask.entity.User2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void shouldReturnItemListWhen() {
        //given
        List<String> items1 = Arrays.asList("1", "2");
        List<String> items2 = Arrays.asList("3", "4");
        Order2 o1 = new Order2();
        o1.setItems(items1);
        Order2 o2 = new Order2();
        o2.setItems(items2);
        User2 u1 = new User2();
        u1.setOrders(Collections.singletonList(o1));
        User2 u2 = new User2();
        u2.setOrders(Collections.singletonList(o2));

        List<User2> users = Arrays.asList(u1, u2);

        List<String> expectedItems = new ArrayList<>();
        expectedItems.add("1");
        expectedItems.add("2");
        expectedItems.add("3");
        expectedItems.add("4");

        //when
        List<String> actualItems = Main.getItems(users);

        //then
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void shouldReturnNotEmptyListWhenItem1IsNull() {
        //given
        List<String> items1 = null;
        List<String> items2 = Arrays.asList("3", "4");
        Order2 o1 = new Order2();
        o1.setItems(items1);
        Order2 o2 = new Order2();
        o2.setItems(items2);
        User2 u1 = new User2();
        u1.setOrders(Collections.singletonList(o1));
        User2 u2 = new User2();
        u2.setOrders(Collections.singletonList(o2));

        List<User2> users = Arrays.asList(u1, u2);

        List<String> expectedItems = new ArrayList<>();
        expectedItems.add("3");
        expectedItems.add("4");

        //when
        List<String> actualItems = Main.getItems(users);

        //then
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void shouldReturnNotEmptyListWhenOrder1IsNull() {
        //given
        List<String> items2 = Arrays.asList("3", "4");
        Order2 o1 = null;
        Order2 o2 = new Order2();
        o2.setItems(items2);
        User2 u1 = new User2();
        u1.setOrders(Collections.singletonList(o1));
        User2 u2 = new User2();
        u2.setOrders(Collections.singletonList(o2));

        List<User2> users = Arrays.asList(u1, u2);

        List<String> expectedItems = new ArrayList<>();
        expectedItems.add("3");
        expectedItems.add("4");

        //when
        List<String> actualItems = Main.getItems(users);

        //then
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void shouldReturnNotEmptyListWhenOrder1IsNull2() {
        //given
        List<String> items2 = Arrays.asList("3", "4");
        Order2 o1 = null;
        Order2 o2 = new Order2();
        o2.setItems(items2);
        User2 u1 = new User2();
        u1.setOrders(null);
        User2 u2 = new User2();
        u2.setOrders(Collections.singletonList(o2));

        List<User2> users = Arrays.asList(u1, u2);

        List<String> expectedItems = new ArrayList<>();
        expectedItems.add("3");
        expectedItems.add("4");

        //when
        List<String> actualItems = Main.getItems(users);

        //then
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void shouldReturnNotEmptyListWhenUser1IsNull() {
        //given
        List<String> items2 = Arrays.asList("3", "4");
        Order2 o2 = new Order2();
        o2.setItems(items2);
        User2 u1 = null;
        User2 u2 = new User2();
        u2.setOrders(Collections.singletonList(o2));

        List<User2> users = Arrays.asList(u1, u2);

        List<String> expectedItems = new ArrayList<>();
        expectedItems.add("3");
        expectedItems.add("4");

        //when
        List<String> actualItems = Main.getItems(users);

        //then
        assertEquals(expectedItems, actualItems);
    }

    @Test
    void shouldReturnEmptyListIfUsersIsNull() {
        //given
        List<String> expectedItems = Collections.emptyList();

        //when
        List<String> actualItems = Main.getItems(null);

        //then
        assertEquals(expectedItems, actualItems);
    }

}