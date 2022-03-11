package com.example.springboottask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "create_time")
    @CreationTimestamp
    private LocalDateTime createTime;

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
