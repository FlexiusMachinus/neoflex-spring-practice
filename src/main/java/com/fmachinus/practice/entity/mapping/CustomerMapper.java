package com.fmachinus.practice.entity.mapping;

import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.entity.dto.CustomerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto fromCustomer(Customer customer);

    Customer toCustomer(CustomerDto customerDto);

    List<CustomerDto> fromCustomerCollection(List<Customer> customerList);

    List<Customer> toCustomerCollection(List<CustomerDto> customerDtoList);
}
