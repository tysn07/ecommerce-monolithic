package com.sparta.ecommerceproject.order.repository;

import com.sparta.ecommerceproject.order.entity.OrderDetail;
import com.sparta.ecommerceproject.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findOrderDetailsByOrderId(Long orderId);
}
