package com.fmachinus.practice.service;

import com.fmachinus.practice.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Long> findAllIds();

    Order placeOrder(Order order);
    Order replaceAt(Long id, Order newOrder);
    void delete(Long id);

    List<Order> findAll();

    boolean existsById(Long id);
    Order findById(Long id);

    List<Order> findByCustomerId(Long customerId);
    List<Order> findByProductId(Long productId);

    List<Order> findByPurchaseDateBetween(LocalDateTime date1, LocalDateTime date2);
}
