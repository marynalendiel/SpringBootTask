package com.example.springboottask.service;

import com.example.springboottask.converter.OrderEntityConverter;
import com.example.springboottask.entity.OrderEntity;
import com.example.springboottask.model.Order;
import com.example.springboottask.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderEntityConverter orderEntityConverter;

    @Override
    public List<Order> getOrders() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        return orderEntityList.stream()
                .map(orderEntity -> orderEntityConverter.convertToModel(orderEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<OrderEntity> orderEntityList = orderRepository.findOrdersByUserId(userId);
        return orderEntityList.stream()
                .map(orderEntity -> orderEntityConverter.convertToModel(orderEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = orderEntityConverter.convertToEntity(order);
        orderRepository.save(orderEntity);
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
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        return orderEntityConverter.convertToModel(orderEntity);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
