package com.fmachinus.practice.entity.mapping;

import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.entity.dto.OrderDto;
import com.fmachinus.practice.service.CustomerService;
import com.fmachinus.practice.service.ProductService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = { CustomerMapper.class, ProductMapper.class, CustomerService.class, ProductService.class })
public abstract class OrderMapper {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    public abstract OrderDto fromOrder(Order order);

    public Order toOrder(OrderDto orderDto) {

        if (orderDto == null || orderDto.getCustomer() == null || orderDto.getProduct() == null) {
            return null;
        }

        Customer customer = customerService.findById(orderDto.getCustomer().getId());
        Product product = productService.findById(orderDto.getProduct().getId());

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(customer);
        order.setProduct(product);
        order.setPurchaseDate(orderDto.getPurchaseDate());

        return order;
    }

    public abstract List<OrderDto> fromOrderCollection(List<Order> orderList);

    public abstract List<Order> toOrderCollection(List<OrderDto> orderDtoList);
}
