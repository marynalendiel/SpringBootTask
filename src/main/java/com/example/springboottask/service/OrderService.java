package com.example.springboottask.service;

import com.example.springboottask.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    List<Order> getUserOrders(int userId);

    void updateOrder(int id, BigDecimal price);

    void saveOrder(Order order);

    Order getOrder(int orderId);

    void deleteOrder(int orderId);
}
