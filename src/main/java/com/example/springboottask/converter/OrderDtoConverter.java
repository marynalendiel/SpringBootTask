package com.example.springboottask.converter;

import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.model.Order;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {

    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();

        if (order != null) {
            orderDto.setId(order.getId());
            orderDto.setUserId(order.getUserId());
            orderDto.setPrice(order.getPrice());
            orderDto.setCreateTime(order.getCreateTime());

            return orderDto;
        }
        return null;
    }

    public List<OrderDto> toDto(List<Order> orders) {
        return ListUtils.emptyIfNull(orders).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Order toModel(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        var order = new Order();
        order.setId(orderDto.getId());
        order.setUserId(orderDto.getUserId());
        order.setPrice(orderDto.getPrice());
        order.setCreateTime(orderDto.getCreateTime());
        return order;
    }

    public List<Order> toModel(List<OrderDto> orderDtoList) {
        return ListUtils.emptyIfNull(orderDtoList).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
