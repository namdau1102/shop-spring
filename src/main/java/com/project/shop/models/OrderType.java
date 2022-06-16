package com.project.shop.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_types")
@Data
@NoArgsConstructor
public class OrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static enum OrderStatus {
        ORDER_ORDERED,
        ORDER_COMPLETED,
        ORDER_CANCELED
    }
}
