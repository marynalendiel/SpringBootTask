package com.example.springboottask.repository;

import com.example.springboottask.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.userId = ?1")
    List<Order> findOrdersByUserId(Long userId);
}
