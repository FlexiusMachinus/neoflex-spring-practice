package com.fmachinus.practice.repository;

import com.fmachinus.practice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByPrice(Integer price);

    List<Product> findByPriceLessThan(Integer price);

    List<Product> findByPriceGreaterThan(Integer price);

    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
}
