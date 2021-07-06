package com.fmachinus.practice.controller;

import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.dto.CustomerDto;
import com.fmachinus.practice.dto.mapping.CustomerMapper;
import com.fmachinus.practice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService service;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerController(CustomerService service, CustomerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/customers")
    public List<CustomerDto> findAllCustomers() {
        return mapper.fromCustomerCollection(service.findAll());
    }

    @GetMapping("/customers/{id}")
    public CustomerDto findCustomerById(@PathVariable Long id) {
        return mapper.fromCustomer(service.findById(id));
    }

    @PostMapping("/customers")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = mapper.toCustomer(customerDto);
        return mapper.fromCustomer(service.add(customer));
    }

    @PutMapping("/customers/{id}")
    public CustomerDto replaceCustomer(@RequestBody CustomerDto newCustomerDto, @PathVariable Long id) {
        Customer newCustomer = mapper.toCustomer(newCustomerDto);
        return mapper.fromCustomer(service.replaceAt(id, newCustomer));
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.deleteById(id);
    }
}
