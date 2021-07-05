package com.fmachinus.practice.service;

import com.fmachinus.practice.entity.Product;

import java.util.List;

public interface ProductService {

    Product add(Product product);
    Product replaceAt(Long id, Product newProduct);
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