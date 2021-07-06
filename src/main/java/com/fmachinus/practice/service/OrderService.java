package com.fmachinus.practice.service;

import com.fmachinus.practice.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order add(Order order);
    Order replaceAt(Long id, Order newOrder);
    void delete(Long id);

    List<Order> findAll();
    List<Order> findAllById(List<Long> id);

    boolean existsById(Long id);
    Order findById(Long id);

    List<Order> findByCustomerId(Long customerId);
    List<Order> findByProductId(Long productId);
    List<Order> findByCustomerIdAndProductId(Long customerId, Long productId);

    List<Order> findByPurchaseDateLessThan(LocalDateTime date);
    List<Order> findByPurchaseDateGreaterThan(LocalDateTime date);
    List<Order> findByPurchaseDateBetween(LocalDateTime date1, LocalDateTime date2);
}
