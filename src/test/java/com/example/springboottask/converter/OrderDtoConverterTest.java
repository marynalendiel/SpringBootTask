package com.example.springboottask.converter;

import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class OrderDtoConverterTest {

    @Autowired
    OrderDtoConverter orderDtoConverter;

    @Test
    void shouldConvertToDtoIfModelIsGiven() {
        var order = createOrderModel();
        OrderDto actualOrderDto = orderDtoConverter.toDto(order);
        OrderDto expectedOrderDto = createOrderDto();

        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToDto() {
        OrderDto actualOrderDto = orderDtoConverter.toDto((Order) null);

        assertNull(actualOrderDto);
    }

    @Test
    void shouldConvertToDtoListIfModelListIsGiven() {
        var orders = Collections.singletonList(createOrderModel());
        List<OrderDto> actualOrderDtoList = orderDtoConverter.toDto(orders);
        List<OrderDto> expectedOrderDtoList = Collections.singletonList(createOrderDto());

        assertEquals(expectedOrderDtoList, actualOrderDtoList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToDtoList() {
        List<OrderDto> actualOrderDtoList = orderDtoConverter.toDto((List<Order>) null);

        assertTrue(actualOrderDtoList.isEmpty());
    }

    @Test
    void shouldConvertToModelIfDtoIsGiven() {
        OrderDto orderDto = createOrderDto();
        Order actualOrder = orderDtoConverter.toModel(orderDto);
        Order expectedOrder = createOrderModel();

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        Order actualOrderDto = orderDtoConverter.toModel((OrderDto) null);

        assertNull(actualOrderDto);
    }

    @Test
    void shouldConvertToModelListIfDtoListIsGiven() {
        List<OrderDto> orderDtoList = Collections.singletonList(createOrderDto());
        List<Order> actualOrderList = orderDtoConverter.toModel(orderDtoList);
        List<Order> expectedOrderList = Collections.singletonList(createOrderModel());

        assertEquals(expectedOrderList, actualOrderList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToModelList() {
        List<Order> actualOrderDtoList = orderDtoConverter.toModel((List<OrderDto>) null);

        assertTrue(actualOrderDtoList.isEmpty());
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