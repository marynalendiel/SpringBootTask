package com.example.springboottask.service;

import com.example.springboottask.entity.Order;
import com.example.springboottask.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        Order orderFromDb = orderRepository.getById(order.getId());

        orderFromDb.setPrice(order.getPrice());
        orderRepository.save(orderFromDb);

        return orderFromDb;
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
