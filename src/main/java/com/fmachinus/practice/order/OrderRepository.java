package com.fmachinus.practice.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByProductId(Long productId);

    List<Order> findByCustomerIdAndProductId(Long customerId, Long productId);

    List<Order> findByDateLessThan(LocalDateTime date);

    List<Order> findByDateGreaterThan(LocalDateTime date);

    List<Order> findByDateBetween(LocalDateTime date1, LocalDateTime date2);
}