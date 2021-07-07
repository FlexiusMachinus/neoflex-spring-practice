package com.fmachinus.practice.repository;

import com.fmachinus.practice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id FROM Order o")
    List<Long> findAllIds();

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByProductId(Long productId);

    List<Order> findByPurchaseDateBetween(LocalDateTime date1, LocalDateTime date2);
}