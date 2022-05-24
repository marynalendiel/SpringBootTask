package com.example.springboottask.converter;

import com.example.springboottask.entity.OrderEntity;
import com.example.springboottask.model.Order;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEntityConverter {

    public Order toModel(OrderEntity orderEntity) {
        Order order = new Order();

        if (orderEntity != null) {
            order.setId(orderEntity.getId());
            order.setUserId(orderEntity.getUserId());
            order.setPrice(orderEntity.getPrice());
            order.setCreateTime(orderEntity.getCreateTime());

            return order;
        }
        return null;
    }

    public List<Order> toModel(List<OrderEntity> orderEntityList) {
        return ListUtils.emptyIfNull(orderEntityList).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        if (order != null) {
            orderEntity.setId(order.getId());
            orderEntity.setUserId(order.getUserId());
            orderEntity.setPrice(order.getPrice());
            orderEntity.setCreateTime(order.getCreateTime());

            return orderEntity;
        }
        return null;
    }

    public List<OrderEntity> toEntity(List<Order> orders) {
        return ListUtils.emptyIfNull(orders).stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
