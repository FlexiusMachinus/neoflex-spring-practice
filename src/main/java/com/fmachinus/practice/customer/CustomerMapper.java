package com.fmachinus.practice.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDto fromCustomer(Customer customer);
    Customer toCustomer(CustomerDto customerDto);
}
