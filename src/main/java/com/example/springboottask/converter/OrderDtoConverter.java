package com.example.springboottask.converter;

import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.model.Order;
import org.apache.commons.collections4.ListUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {

    public OrderDto convertToDto(Order order) {
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

    public List<OrderDto> convertToDtoList(List<Order> orders) {
        return ListUtils.emptyIfNull(orders).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Order convertToModel(OrderDto orderDto) {
        Order order = new Order();

        if (orderDto != null) {
            order.setId(orderDto.getId());
            order.setUserId(orderDto.getUserId());
            order.setPrice(orderDto.getPrice());
            order.setCreateTime(orderDto.getCreateTime());

            return order;
        }
        return null;
    }

    public List<Order> convertToModelList(List<OrderDto> orderDtoList) {
        return ListUtils.emptyIfNull(orderDtoList).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }
}
