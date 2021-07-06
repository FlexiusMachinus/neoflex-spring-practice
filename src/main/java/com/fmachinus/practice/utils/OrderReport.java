package com.fmachinus.practice.utils;

import com.fmachinus.practice.entity.Order;
import com.fmachinus.practice.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter(AccessLevel.PROTECTED)
public class OrderReport {

    @Getter(AccessLevel.NONE)
    private List<Order> orders;

    @Getter(AccessLevel.NONE)
    private Map<Product, Integer> productSales;

    private BigDecimal revenue;

    public OrderReport(List<Order> orders) {
        this.orders = orders;
        this.productSales = new HashMap<>();
        this.revenue = BigDecimal.ZERO;

        for (Order order : orders) {
            Product product = order.getProduct();
            Integer sales = productSales.get(product);
            productSales.put(product, (sales == null) ? 1 : sales + 1);
            revenue = revenue.add(product.getPrice());
        }
    }

    public List<Order> getOrdersReadonly() {
        return Collections.unmodifiableList(orders);
    }

    public Map<Product, Integer> getProductSalesReadonly() {
        return Collections.unmodifiableMap(productSales);
    }

    public int getTotalSales() {
        return orders.size();
    }

    public BigDecimal getRevenueByProduct(Product product) {
        if (!productSales.containsKey(product)) {
            return BigDecimal.ZERO;
        }

        BigDecimal sales = BigDecimal.valueOf(productSales.get(product));
        return product.getPrice().multiply(sales);
    }

    public Product getBestProductBySales() {

        Integer maxSales = 0;
        Product bestSeller = null;

        for (Product product : productSales.keySet()) {
            Integer sales = productSales.get(product);
            if (sales > maxSales) {
                maxSales = sales;
                bestSeller = product;
            }
        }

        return bestSeller;
    }

    public Product getBestProductByRevenue() {

        BigDecimal maxRevenue = BigDecimal.ZERO;
        Product bestSeller = null;

        for (Product product : productSales.keySet()) {
            BigDecimal sales = BigDecimal.valueOf(productSales.get(product));
            BigDecimal revenue = product.getPrice().multiply(sales);
            if (revenue.compareTo(maxRevenue) > 0) {
                maxRevenue = revenue;
                bestSeller = product;
            }
        }

        return bestSeller;
    }
}
