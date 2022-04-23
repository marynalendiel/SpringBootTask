package com.example.springboottask.repository;

import com.example.springboottask.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.userId = ?1")
    List<OrderEntity> findOrdersByUserId(Long userId);
}
