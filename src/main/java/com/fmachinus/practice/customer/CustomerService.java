package com.fmachinus.practice.customer;

import java.util.List;

public interface CustomerService {

    long count();

    Customer add(Customer customer);
    void deleteById(Long id);

    List<Customer> findAll();
    List<Customer> findAllById(List<Long> id);

    boolean existsById(Long id);
    Customer findById(Long id);

    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
