package com.fmachinus.practice.controller;

import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.dto.OrderDto;
import com.fmachinus.practice.entity.mapping.OrderMapper;
import com.fmachinus.practice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    @Autowired
    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("orders")
    public List<OrderDto> findAllOrders() {
        return mapper.fromOrderCollection(service.findAll());
    }

    @GetMapping("orders/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        return mapper.fromOrder(service.findById(id));
    }

    @PostMapping("orders")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        Order order = mapper.toOrder(orderDto);
        return mapper.fromOrder(service.placeOrder(order));
    }

    @PutMapping("orders/{id}")
    public OrderDto replaceOrder(@RequestBody OrderDto newOrderDto, @PathVariable Long id) {
        Order newOrder = mapper.toOrder(newOrderDto);
        return mapper.fromOrder(service.replaceAt(id, newOrder));
    }

    @DeleteMapping("orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("customers/{customerId}/orders")
    public List<OrderDto> findAllCustomerOrders(@PathVariable Long customerId) {
        return mapper.fromOrderCollection(service.findByCustomerId(customerId));
    }
}
