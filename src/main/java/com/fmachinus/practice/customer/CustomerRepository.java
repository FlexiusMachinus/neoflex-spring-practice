package com.fmachinus.practice.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
