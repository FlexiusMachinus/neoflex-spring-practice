package com.fmachinus.practice.service;

import com.fmachinus.practice.entity.Product;

import java.util.List;

public interface ProductService {

    List<Long> findAllIds();

    Product add(Product product);
    Product replaceAt(Long id, Product newProduct);
    void deleteById(Long id);

    List<Product> findAll();

    boolean existsById(Long id);
    Product findById(Long id);

    List<Product> findByName(String name);
}