package com.example.springboottask.converter;

import com.example.springboottask.entity.OrderEntity;
import com.example.springboottask.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class OrderEntityConverterTest {

    @Autowired
    OrderEntityConverter orderEntityConverter;

    @Test
    void shouldConvertToModelIfEntityIsGiven() {
        OrderEntity orderEntity = createOrderEntity();
        Order actualOrder = orderEntityConverter.toModel(orderEntity);
        Order expectedOrder = createOrderModel();

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToModel() {
        Order actualOrder = orderEntityConverter.toModel((OrderEntity) null);

        assertNull(actualOrder);
    }

    @Test
    void shouldConvertToModelListIfEntityListIsGiven() {
        List<OrderEntity> orderEntityList = Collections.singletonList(createOrderEntity());
        List<Order> actualOrderList = orderEntityConverter.toModel(orderEntityList);
        List<Order> expectedOrderList = Collections.singletonList(createOrderModel());

        assertEquals(expectedOrderList, actualOrderList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToModelList() {
        List<Order> actualOrderList = orderEntityConverter.toModel((List<OrderEntity>) null);

        assertTrue(actualOrderList.isEmpty());
    }

    @Test
    void shouldConvertToEntityIfModelIsGiven() {
        Order order = createOrderModel();
        OrderEntity actualOrderEntity = orderEntityConverter.toEntity(order);
        OrderEntity expectedOrderEntity = createOrderEntity();

        assertEquals(expectedOrderEntity, actualOrderEntity);
    }

    @Test
    void shouldReturnNullIfNullIsGivenToConvertToEntity() {
        OrderEntity actualOrderEntity = orderEntityConverter.toEntity((Order) null);

        assertNull(actualOrderEntity);
    }

    @Test
    void shouldConvertToEntityListIfModelListIsGiven() {
        List<Order> orders = Collections.singletonList(createOrderModel());
        List<OrderEntity> actualOrderEntityList = orderEntityConverter.toEntity(orders);
        List<OrderEntity> expectedOrderEntityList = Collections.singletonList(createOrderEntity());

        assertEquals(expectedOrderEntityList, actualOrderEntityList);
    }

    @Test
    void shouldReturnEmptyListIfNullIsGivenToConvertToEntityList() {
        List<OrderEntity> actualOrderEntityList = orderEntityConverter.toEntity((List<Order>) null);

        assertTrue(actualOrderEntityList.isEmpty());
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