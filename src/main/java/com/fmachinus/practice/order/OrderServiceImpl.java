package com.fmachinus.practice.order;

import com.fmachinus.practice.customer.CustomerService;
import com.fmachinus.practice.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    public Order add(Order order) {
        if (!validateOrder(order)) {
            log.error("Не удалось сформировать заказ: указанный покупатель или товар отсутствует в базе.");
            return null;
        }

        Order newOrder = repository.save(order);
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

            return oldOrder;
        }

        // Иначе добавить новый заказ в БД
        return add(newOrder);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
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

    public List<Order> findByCustomerIdAndProductId(Long customerId, Long productId) {
        return repository.findByCustomerIdAndProductId(customerId, productId);
    }

    public List<Order> findByPurchaseDateLessThan(LocalDateTime date) {
        return repository.findByPurchaseDateLessThan(date);
    }

    public List<Order> findByPurchaseDateGreaterThan(LocalDateTime date) {
        return repository.findByPurchaseDateGreaterThan(date);
    }

    public List<Order> findByPurchaseDateBetween(LocalDateTime date1, LocalDateTime date2) {
        return repository.findByPurchaseDateBetween(date1, date2);
    }

    // Проверяет в БД наличие покупателя и товара, указанных в заказе
    private boolean validateOrder(Order order) {
        return customerService.existsById(order.getCustomer().getId()) &&
                productService.existsById(order.getProduct().getId());
    }
}
