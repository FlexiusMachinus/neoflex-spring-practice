package com.fmachinus.practice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("orders/")
    public List<Order> findAllOrders() {
        return service.findAll();
    }

    @GetMapping("orders/{id}")
    public Order findOrderById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("orders")
    public Order addOrder(@RequestBody Order order, @PathVariable Long id) {
        return service.add(order);
    }

    @PutMapping("orders/{id}")
    public Order replaceOrder(@RequestBody Order newOrder, @PathVariable Long id) {

        Order oldOrder = service.findById(id);

        if (oldOrder != null) {
            oldOrder.setCustomer(newOrder.getCustomer());
            oldOrder.setProduct(newOrder.getProduct());
            oldOrder.setPurchaseDate(newOrder.getPurchaseDate());
            return oldOrder;
        }

        return service.add(newOrder);
    }

    @DeleteMapping("orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteById(id);
    }
}
