package com.project.shop.repositories;

import com.project.shop.models.Order;
import com.project.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    boolean existsByOrderId(Integer orderId);

    List<Order> findAllByOrderId(Integer orderId);

}
