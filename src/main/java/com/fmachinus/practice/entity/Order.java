package com.fmachinus.practice.entity;

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
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime purchaseDate;

    Order() { }

    public Order(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
        this.purchaseDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {

        if (this.customer != null) {
            this.customer.removeOrder(this);
        }

        this.customer = customer;
        this.customer.addOrder(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order)o;
        return (id.equals(order.id) && customer.equals(order.customer) &&
                product.equals(order.product) && purchaseDate.equals(order.purchaseDate));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer.getId(), product.getId(), purchaseDate);
    }

    @Override
    public String toString() {
        String format = "Order@%d[customerId=%d, productId=%d, date=%s]";
        String formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(purchaseDate);
        return String.format(format, id, customer.getId(), product.getId(), formattedDateTime);
    }
}
