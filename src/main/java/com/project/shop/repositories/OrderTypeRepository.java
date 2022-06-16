package com.project.shop.repositories;

import com.project.shop.models.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {
    OrderType findOrderTypeByOrderStatus(OrderType.OrderStatus orderStatus);
}
