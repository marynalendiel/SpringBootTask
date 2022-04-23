package com.example.springboottask.converter;

import com.example.springboottask.dto.OrderDto;
import com.example.springboottask.entity.OrderEntity;
import com.example.springboottask.entity.UserEntity;
import com.example.springboottask.model.Order;
import com.example.springboottask.model.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderEntityConverter.class)
class OrderEntityConverterTest {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderEntityConverter orderEntityConverter;

    @Test
    void shouldConvertToModelIfEntityIsGiven() {
        OrderEntity orderEntity = createOrderEntity();
        Order actualOrder = orderEntityConverter.convertToModel(orderEntity);
        Order expectedOrder = createOrderModel();

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        OrderEntity orderEntity = null;
        Order actualOrder = orderEntityConverter.convertToModel(orderEntity);

        assertNull(actualOrder);
    }

    @Test
    void shouldConvertToEntityIfModelIsGiven() {
        Order order = createOrderModel();
        OrderEntity actualOrderEntity = orderEntityConverter.convertToEntity(order);
        OrderEntity expectedOrderEntity = createOrderEntity();

        assertEquals(expectedOrderEntity, actualOrderEntity);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToEntity() {
        Order order = null;
        OrderEntity actualOrderEntity = orderEntityConverter.convertToEntity(order);

        assertNull(actualOrderEntity);
    }

    private Order createOrderModel() {
        Order order = new Order();
        order.setId(1L);
        order.setUserId(2L);
        order.setPrice(BigDecimal.valueOf(123));
        return order;
    }

    private OrderEntity createOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setUserId(2L);
        orderEntity.setPrice(BigDecimal.valueOf(123));
        return orderEntity;
    }
}