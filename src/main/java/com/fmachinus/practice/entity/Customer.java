package com.fmachinus.practice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal cash;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Set<Order> getOrdersReadonly() {
        return Collections.unmodifiableSet(orders);
    }

    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    public boolean removeOrder(Order order) {
        return orders.remove(order);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer)o;
        return (id.equals(customer.id) && firstName.equals(customer.firstName) && lastName.equals(customer.lastName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("Customer@%d[firstName=%s, lastName=%s, cash=%s]", id, firstName, lastName, cash);
    }
}
