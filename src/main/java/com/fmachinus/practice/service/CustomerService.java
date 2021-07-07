package com.fmachinus.practice.service;

import com.fmachinus.practice.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Long> findAllIds();

    Customer add(Customer customer);
    Customer replaceAt(Long id, Customer newCustomer);
    void deleteById(Long id);

    List<Customer> findAll();

    boolean existsById(Long id);
    Customer findById(Long id);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
