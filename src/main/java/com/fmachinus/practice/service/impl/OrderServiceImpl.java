package com.fmachinus.practice.service.impl;

import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.repository.OrderRepository;
import com.fmachinus.practice.service.CustomerService;
import com.fmachinus.practice.service.OrderService;
import com.fmachinus.practice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, CustomerService customerService, ProductService productService) {
        this.repository = repository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public List<Long> findAllIds() {
        return repository.findAllIds();
    }

    @Transactional
    public Order placeOrder(Order order) {

        if (!validateOrder(order)) {
            log.error("Не удалось сформировать заказ: указанный покупатель или товар отсутствует в базе.");
            return null;
        }

        BigDecimal cash = order.getCustomer().getCash();
        BigDecimal price = order.getProduct().getPrice();
        int quantity = order.getProduct().getQuantity();

        if (order.getProduct().getQuantity() < 1) {
            log.error("Не удалось сформировать заказ: товара нет на складе.");
            return null;
        }

        if (cash.compareTo(price) < 0) {
            log.error("Не удалось сформировать заказ: у покупателя недостаточно средств.");
            return null;
        }

        Order newOrder = repository.save(order);
        newOrder.getProduct().setQuantity(quantity - 1);
        newOrder.getCustomer().setCash(cash.subtract(price));
        newOrder.getCustomer().addOrder(newOrder);

        log.info(String.format("Заказ #%d успешно сформирован!", newOrder.getId()));

        return newOrder;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Order replaceAt(Long id, Order newOrder) {
        Order oldOrder = findById(id);

        // Если заказ с таким id существует, изменить его
        if (oldOrder != null) {
            if (validateOrder(newOrder)) {
                oldOrder.setCustomer(newOrder.getCustomer());
                oldOrder.setProduct(newOrder.getProduct());
                oldOrder.setPurchaseDate(newOrder.getPurchaseDate());
            }

            return repository.save(oldOrder);
        }

        // Иначе добавить новый заказ в БД
        return placeOrder(newOrder);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
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

    public List<Order> findByPurchaseDateBetween(LocalDateTime date1, LocalDateTime date2) {
        return repository.findByPurchaseDateBetween(date1, date2);
    }

    // Проверяет в БД наличие покупателя и товара, указанных в заказе
    private boolean validateOrder(Order order) {
        return (order.getCustomer() != null && customerService.existsById(order.getCustomer().getId()) &&
                order.getProduct() != null && productService.existsById(order.getProduct().getId()));
    }
}
