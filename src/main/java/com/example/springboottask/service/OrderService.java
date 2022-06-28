package com.example.springboottask.service;

import com.example.springboottask.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    List<Order> getUserOrders(Long userId);

    Order updateOrder(Order order);

    void saveOrder(Order order);

    Order getOrder(Long orderId);

    void deleteOrder(Long orderId);
}
