package com.fmachinus.practice.entity.mapping;

import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.entity.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDto fromCustomer(Customer customer);
    Customer toCustomer(CustomerDto customerDto);
}
