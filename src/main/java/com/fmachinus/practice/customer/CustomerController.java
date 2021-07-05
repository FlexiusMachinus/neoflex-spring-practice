package com.fmachinus.practice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return service.findAll();
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return service.add(customer);
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomer(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/customers/{id}")
    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

        Customer customer = service.findById(id);

        if (customer != null) {
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            return customer;
        }

        return service.add(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.deleteById(id);
    }
}
