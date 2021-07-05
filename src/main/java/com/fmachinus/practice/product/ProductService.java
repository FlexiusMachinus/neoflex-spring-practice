package com.fmachinus.practice.product;

import java.util.List;

public interface ProductService {

    long count();

    Product add(Product product);
    void deleteById(Long id);

    List<Product> findAll();
    List<Product> findAllById(List<Long> id);

    boolean existsById(Long id);
    Product findById(Long id);

    List<Product> findByName(String name);

    List<Product> findByPrice(Integer price);
    List<Product> findByPriceLessThan(Integer price);
    List<Product> findByPriceGreaterThan(Integer price);
    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
}