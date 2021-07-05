package com.fmachinus.practice.order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order add(Order order);
    void deleteById(Long id);

    List<Order> findAll();

    List<Order> findAllById(List<Long> id);
    Order findById(Long id);

    List<Order> findByCustomerId(Long customerId);
    List<Order> findByProductId(Long productId);
    List<Order> findByCustomerIdAndProductId(Long customerId, Long productId);

    List<Order> findByDateLessThan(LocalDateTime date);
    List<Order> findByDateGreaterThan(LocalDateTime date);
    List<Order> findByDateBetween(LocalDateTime date1, LocalDateTime date2);
}
