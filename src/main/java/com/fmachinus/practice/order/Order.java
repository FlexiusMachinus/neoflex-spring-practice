package com.fmachinus.practice.order;

import com.fmachinus.practice.customer.Customer;
import com.fmachinus.practice.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private LocalDateTime date;

    Order() { }

    public Order(Customer customer, Product product) {
        this.setCustomer(customer);
        this.product = product;
        this.date = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer.removeOrder(this);
        this.customer = customer;
        customer.addOrder(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getPurchaseDate() {
        return date;
    }

    public void setPurchaseDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order)o;
        return (id.equals(order.id) && customer.equals(order.customer) &&
                product.equals(order.product) && date.equals(order.date));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer.getId(), product.getId(), date);
    }

    @Override
    public String toString() {
        String format = "Order@%d[customerId=%d, productId=%d, date=%s]";
        String formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
        return String.format(format, id, customer.getId(), product.getId(), formattedDateTime);
    }
}
