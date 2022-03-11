package com.example.springboottask.service;

import com.example.springboottask.entity.Order;
import com.example.springboottask.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAllOrders();
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(int id, BigDecimal price) {
        orderRepository.updatePriceById(price, id);
    }

    @Override
    public Order getOrder(int orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}
