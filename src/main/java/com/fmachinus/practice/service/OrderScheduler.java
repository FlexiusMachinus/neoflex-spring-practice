package com.fmachinus.practice.service;

import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.service.CustomerService;
import com.fmachinus.practice.service.OrderService;
import com.fmachinus.practice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Configuration
@EnableScheduling
public class OrderScheduler {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;

    private final Random rand = new Random();

    @Autowired
    public OrderScheduler(CustomerService customerService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void placeRandomOrder() {

        List<Long> customerIds = customerService.findAllIds();
        List<Long> productIds = productService.findAllIds();

        Customer randomCustomer = customerService.findById(customerIds.get(rand.nextInt(customerIds.size())));
        Product randomProduct = productService.findById(productIds.get(rand.nextInt(productIds.size())));
        Order randomOrder = new Order(randomCustomer, randomProduct);

        log.info(String.format("Покупатель %s %s отправил запрос на покупку товара %s", randomCustomer.getFirstName(), randomCustomer.getLastName(), randomProduct.toString()));

        orderService.placeOrder(randomOrder);
    }
}
