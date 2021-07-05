package com.fmachinus.practice.entity.mapping;

import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { CustomerMapper.class, ProductMapper.class })
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDto fromOrder(Order order);
    Order toOrder(OrderDto orderDto);
}
