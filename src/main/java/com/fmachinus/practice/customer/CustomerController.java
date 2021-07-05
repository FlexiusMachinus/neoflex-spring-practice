package com.fmachinus.practice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public List<CustomerDto> findAllCustomers() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        List<Customer> customerList = service.findAll();
        customerList.forEach(customer -> customerDtoList.add(CustomerMapper.MAPPER.fromCustomer(customer)));
        return customerDtoList;
    }

    @GetMapping("/customers/{id}")
    public CustomerDto findCustomerById(@PathVariable Long id) {
        return CustomerMapper.MAPPER.fromCustomer(service.findById(id));
    }

    @PostMapping("/customers")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = CustomerMapper.MAPPER.toCustomer(customerDto);
        return CustomerMapper.MAPPER.fromCustomer(service.add(customer));
    }

    @PutMapping("/customers/{id}")
    public CustomerDto replaceCustomer(@RequestBody CustomerDto newCustomerDto, @PathVariable Long id) {
        Customer newCustomer = CustomerMapper.MAPPER.toCustomer(newCustomerDto);
        return CustomerMapper.MAPPER.fromCustomer(service.replaceAt(id, newCustomer));
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.deleteById(id);
    }
}
