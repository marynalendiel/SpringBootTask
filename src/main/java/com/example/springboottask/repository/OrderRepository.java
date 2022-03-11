package com.example.springboottask.repository;

import com.example.springboottask.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o")
    List<Order> findAllOrders();

    @Query("select o from Order o where o.userId = ?1")
    List<Order> findOrdersByUserId(int userId);

    @Query("select o from Order o where o.id = ?1")
    Order findOrderById(int orderId);

    @Transactional
    @Modifying
    @Query("update Order o set o.price = ?1 where o.id = ?2")
    void updatePriceById(BigDecimal price, int id);



    @Transactional
    @Modifying
    @Query("delete from Order o where o.id = ?1")
    void deleteOrderById(int orderId);
}
