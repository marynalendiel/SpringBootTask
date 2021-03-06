package com.example.springboottask.controller;

import com.example.springboottask.entity.Order;
import com.example.springboottask.service.OrderService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/")
    public List<Order> getOrders() {

        return orderService.getOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {

        Order order = orderService.getOrder(orderId);

        if (order == null) {
            throw new EntityResultNotFoundException("order id not found - " + orderId);
        }

        return order;
    }

    @PostMapping("/")
    public Order addOrder(@RequestBody Order order) {
        orderService.saveOrder(order);

        return order;
    }

    @PutMapping("/")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);

    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);

        if (order == null) {
            throw new EntityResultNotFoundException("order id not found - " + orderId);
        }

        orderService.deleteOrder(orderId);

        return "Deleted order id - " + orderId;
    }
}
