package com.example.springboottask.service;

import com.example.springboottask.controller.EntityResultNotFoundException;
import com.example.springboottask.converter.OrderEntityConverter;
import com.example.springboottask.entity.OrderEntity;
import com.example.springboottask.model.Order;
import com.example.springboottask.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderEntityConverter orderEntityConverter;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderEntityConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId).stream()
                .map(orderEntityConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(orderEntityConverter.convertToEntity(order));
    }

    @Override
    public Order updateOrder(Order order) {
        OrderEntity orderEntity = orderEntityConverter.convertToEntity(order);
        OrderEntity orderFromDb = orderRepository.getById(orderEntity.getId());

        orderFromDb.setPrice(orderEntity.getPrice());
        orderRepository.save(orderFromDb);

        return orderEntityConverter.convertToModel(orderFromDb);
    }

    @Override
    public Order getOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new EntityResultNotFoundException("Order not found"));
        return Optional.ofNullable(orderEntityConverter.convertToModel(orderEntity))
                .orElseThrow(() -> new EntityResultNotFoundException("order id not found - " + orderId));
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
