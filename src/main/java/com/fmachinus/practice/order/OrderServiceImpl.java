package com.fmachinus.practice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    public Order add(Order order) {
        return repository.save(order);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public Order findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Order> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<Order> findByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    public List<Order> findByCustomerIdAndProductId(Long customerId, Long productId) {
        return repository.findByCustomerIdAndProductId(customerId, productId);
    }

    public List<Order> findByDateLessThan(LocalDateTime date) {
        return repository.findByDateLessThan(date);
    }

    public List<Order> findByDateGreaterThan(LocalDateTime date) {
        return repository.findByDateGreaterThan(date);

    }

    public List<Order> findByDateBetween(LocalDateTime date1, LocalDateTime date2) {
        return repository.findByDateBetween(date1, date2);
    }
}
