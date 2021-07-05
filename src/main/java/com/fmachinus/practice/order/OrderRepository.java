package com.fmachinus.practice.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByProductId(Long productId);

    List<Order> findByCustomerIdAndProductId(Long customerId, Long productId);

    List<Order> findByPurchaseDateLessThan(LocalDateTime date);

    List<Order> findByPurchaseDateGreaterThan(LocalDateTime date);

    List<Order> findByPurchaseDateBetween(LocalDateTime date1, LocalDateTime date2);
}