package com.example.springboottask.controller;

import com.example.springboottask.entity.Order;
import com.example.springboottask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getOrders() {

        return orderService.getOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable int orderId) {

        Order order = orderService.getOrder(orderId);

        if (order == null) {
            throw new EntityResultNotFoundException("order id not found - " + orderId);
        }

        return order;
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order) {
        orderService.saveOrder(order);

        return order;
    }

    @PutMapping("/orders")
    public Order updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order.getId(), order.getPrice());
        return getOrder(order.getId());

    }

    @DeleteMapping("/orders/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {
        Order order = orderService.getOrder(orderId);

        if (order == null) {
            throw new EntityResultNotFoundException("order id not found - " + orderId);
        }

        orderService.deleteOrder(orderId);

        return "Deleted order id - " + orderId;
    }
}
