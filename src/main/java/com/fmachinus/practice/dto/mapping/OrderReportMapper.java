package com.fmachinus.practice.dto.mapping;

import com.fmachinus.practice.dto.OrderDto;
import com.fmachinus.practice.dto.OrderReportDto;
import com.fmachinus.practice.dto.ProductDto;
import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.utils.OrderReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", uses = { OrderMapper.class, ProductMapper.class })
public abstract class OrderReportMapper {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Mapping(source = "ordersReadonly", target = "orders", qualifiedByName = "fromOrders")
    @Mapping(source = "productSalesReadonly", target = "productSales", qualifiedByName = "fromProductSales")
    public abstract OrderReportDto fromOrderReport(OrderReport orderReport);

    @Named("fromOrders")
    protected List<OrderDto> fromOrders(List<Order> orders) {
        return orderMapper.fromOrderCollection(orders);
    }

    @Named("fromProductSales")
    protected Map<ProductDto, Integer> fromProductSales(Map<Product, Integer> productSales) {

        Map<ProductDto, Integer> productSalesDto = new HashMap<>();
        for (Product product : productSales.keySet()) {
            productSalesDto.put(productMapper.fromProduct(product), productSales.get(product));
        }

        return productSalesDto;
    }
}
