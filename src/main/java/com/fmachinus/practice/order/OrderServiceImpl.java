package com.fmachinus.practice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository repository;

    Order add(Order order) {
        return repository.save(order);
    }

    void deleteById(Long id) {
        repository.deleteById(id);
    }

    List<Order> findAll() {
        return repository.findAll();
    }

    List<Order> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    Order findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    List<Order> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    List<Order> findByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    List<Order> findByCustomerIdAndProductId(Long customerId, Long productId) {
        return repository.findByCustomerIdAndProductId(customerId, productId);
    }

    List<Order> findByDateLessThan(LocalDateTime date) {
        return repository.findByDateLessThan(date);
    }

    List<Order> findByDateGreaterThan(LocalDateTime date) {
        return repository.findByDateGreaterThan(date);

    }

    List<Order> findByDateBetween(LocalDateTime date1, LocalDateTime date2) {
        return repository.findByDateBetween(date1, date2);
    }
}
