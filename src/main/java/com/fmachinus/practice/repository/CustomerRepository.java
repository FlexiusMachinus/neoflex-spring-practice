package com.fmachinus.practice.repository;

import com.fmachinus.practice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.id FROM Customer c")
    List<Long> findAllIds();

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
