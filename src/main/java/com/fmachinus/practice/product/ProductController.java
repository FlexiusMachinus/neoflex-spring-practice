package com.fmachinus.practice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return service.add(product);
    }

    @PutMapping("/products/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        Product product = service.findById(id);

        if (product != null) {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            return product;
        }

        return service.add(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }
}
