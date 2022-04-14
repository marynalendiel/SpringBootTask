package com.example.springboottask.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
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

        System.out.println(getItems(users));
        Runnable runnable = () -> {
            getItems(null);
            getItems(null);
            getItems(null);
        };

    }

    public static List<String> getItems(List<User2> users) {
        if (users == null) {
            return Collections.emptyList();
        }
//        return users.stream()
//                .filter(Objects::nonNull)
//                .flatMap(user -> {
//                    if (user.getOrders() != null) {
//                        return user.getOrders().stream();
//                    }
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .flatMap(order -> {
//                    if (order.getItems() != null) {
//                        return order.getItems().stream();
//                    }
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());

        return users.stream()
                .filter(Objects::nonNull)
                .flatMap(user -> user.getOrders() != null ? user.getOrders().stream() : null)
                .filter(Objects::nonNull)
                .flatMap(order -> order.getItems() != null ? order.getItems().stream() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
