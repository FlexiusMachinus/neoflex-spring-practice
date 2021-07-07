package com.fmachinus.practice.dto.mapping;

import com.fmachinus.practice.dto.OrderDto;
import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.service.CustomerService;
import com.fmachinus.practice.service.ProductService;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Mapper(componentModel = "spring", uses = { CustomerMapper.class, ProductMapper.class, CustomerService.class, ProductService.class })
public abstract class OrderMapper {

    private CustomerService customerService;
    private ProductService productService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

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
