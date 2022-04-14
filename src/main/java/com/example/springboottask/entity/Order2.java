package com.example.springboottask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order2 {

    private Long id;

    private Long userId;

    private BigDecimal price;

    private LocalDateTime createTime;

    private List<String> items;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + userId +
                ", price=" + price +
                ", createTime=" + createTime +
                '}';
    }
}
