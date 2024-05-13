package com.sparta.ecommerceproject.order.repository;

import com.sparta.ecommerceproject.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);
}
