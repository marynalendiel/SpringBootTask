package com.example.springboottask.converter;

import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.model.Order;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderDtoConverter.class)
class OrderDtoConverterTest {

    @Autowired
    OrderDtoConverter orderDtoConverter;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void shouldConvertToDtoIfModelIsGiven() {
        Order order = createOrderModel();
        OrderDto actualOrderDto = orderDtoConverter.convertToDto(order);
        OrderDto expectedOrderDto = createOrderDto();

        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToDto() {
        Order order = null;
        OrderDto actualOrderDto = orderDtoConverter.convertToDto(order);

        assertNull(actualOrderDto);
    }

    @Test
    void shouldConvertToModelIfDtoIsGiven() {
        OrderDto orderDto = createOrderDto();
        Order actualOrder = orderDtoConverter.convertToModel(orderDto);
        Order expectedOrder = createOrderModel();

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        OrderDto orderDto = null;
        Order actualOrderDto = orderDtoConverter.convertToModel(orderDto);

        assertNull(actualOrderDto);
    }

    private Order createOrderModel() {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(2L);
        order.setPrice(BigDecimal.valueOf(123));
        return order;
    }

    private OrderDto createOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setUserId(2L);
        orderDto.setPrice(BigDecimal.valueOf(123));
        return orderDto;
    }
}