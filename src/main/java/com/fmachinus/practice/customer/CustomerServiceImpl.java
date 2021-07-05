package com.fmachinus.practice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    public long count() {
        return repository.count();
    }

    public Customer add(Customer customer) {
        return repository.save(customer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public List<Customer> findAllById(List<Long> id) {
        return repository.findAllById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Customer findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Customer> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    public List<Customer> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public List<Customer> findByFirstNameOrLastName(String firstName, String lastName) {
        return repository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }
}
