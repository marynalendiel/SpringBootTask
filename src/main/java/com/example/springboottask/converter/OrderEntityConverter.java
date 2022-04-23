package com.example.springboottask.converter;

import com.example.springboottask.entity.OrderEntity;
import com.example.springboottask.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Order convertToModel(OrderEntity orderEntity) {

        return orderEntity != null ? modelMapper.map(orderEntity, Order.class) : null;
    }

    public OrderEntity convertToEntity(Order order) {
        return order != null ? modelMapper.map(order, OrderEntity.class) : null;
    }
}
