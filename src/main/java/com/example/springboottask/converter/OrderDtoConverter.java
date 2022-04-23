package com.example.springboottask.converter;

import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OrderDto convertToDto(Order order) {
        return order != null ? modelMapper.map(order, OrderDto.class) : null;
    }

    public Order convertToModel(OrderDto orderDto) {
        return orderDto != null ? modelMapper.map(orderDto, Order.class) : null;
    }
}
