package com.fmachinus.practice.controller;

import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.dto.OrderDto;
import com.fmachinus.practice.entity.mapping.OrderMapper;
import com.fmachinus.practice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("orders")
    public List<OrderDto> findAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = service.findAll();
        orderList.forEach(order -> orderDtoList.add(OrderMapper.MAPPER.fromOrder(order)));
        return orderDtoList;
    }

    @GetMapping("orders/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        return OrderMapper.MAPPER.fromOrder(service.findById(id));
    }

    @PostMapping("orders")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        Order order = OrderMapper.MAPPER.toOrder(orderDto);
        return OrderMapper.MAPPER.fromOrder(service.add(order));
    }

    @PutMapping("orders/{id}")
    public OrderDto replaceOrder(@RequestBody OrderDto newOrderDto, @PathVariable Long id) {
        Order newOrder = OrderMapper.MAPPER.toOrder(newOrderDto);
        return OrderMapper.MAPPER.fromOrder(service.replaceAt(id, newOrder));
    }

    @DeleteMapping("orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.delete(id);
    }
}
