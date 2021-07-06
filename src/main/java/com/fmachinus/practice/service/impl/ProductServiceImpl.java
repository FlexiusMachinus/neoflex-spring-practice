package com.fmachinus.practice.service.impl;

import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.repository.ProductRepository;
import com.fmachinus.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Long> findAllIds() {
        return repository.findAllIds();
    }

    public Product add(Product product) {
        return repository.save(product);
    }

    public Product replaceAt(Long id, Product newProduct) {
        Product oldProduct = findById(id);

        // Если товар с таким id существует, изменить его
        if (oldProduct != null) {
            oldProduct.setName(newProduct.getName());
            oldProduct.setPrice(newProduct.getPrice());
            oldProduct.setQuantity(newProduct.getQuantity());
            return repository.save(oldProduct);
        }

        // Иначе добавить новый товар в БД
        return add(newProduct);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllById(List<Long> id) {
        return repository.findAllById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> findByPrice(Integer price) {
        return repository.findByPrice(price);
    }

    public List<Product> findByPriceLessThan(Integer price) {
        return repository.findByPriceLessThan(price);
    }

    public List<Product> findByPriceGreaterThan(Integer price) {
        return repository.findByPriceGreaterThan(price);
    }

    public List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    public long count() {
        return repository.count();
    }
}
