package com.fmachinus.practice.order;

import com.fmachinus.practice.customer.CustomerMapper;
import com.fmachinus.practice.product.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { CustomerMapper.class, ProductMapper.class })
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDto fromOrder(Order order);
    Order toOrder(OrderDto orderDto);
}
