package com.fmachinus.practice.service.impl;

import com.fmachinus.practice.entity.Customer;
import com.fmachinus.practice.repository.CustomerRepository;
import com.fmachinus.practice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Long> findAllIds() {
        return repository.findAllIds();
    }

    public Customer add(Customer customer) {
        return repository.save(customer);
    }

    public Customer replaceAt(Long id, Customer newCustomer) {
        Customer oldCustomer = findById(id);

        // Если покупатель с таким id существует, изменить его
        if (oldCustomer != null) {
            oldCustomer.setFirstName(newCustomer.getFirstName());
            oldCustomer.setLastName(newCustomer.getLastName());
            oldCustomer.setCash(newCustomer.getCash());
            return repository.save(oldCustomer);
        }

        // Иначе добавить новую сущность в БД
        return add(newCustomer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Customer findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }
}
