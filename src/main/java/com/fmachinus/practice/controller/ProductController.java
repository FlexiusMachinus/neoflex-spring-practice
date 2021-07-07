package com.fmachinus.practice.controller;

import com.fmachinus.practice.dto.ProductDto;
import com.fmachinus.practice.dto.mapping.ProductMapper;
import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/products")
    public List<ProductDto> findAllProducts() {
        return mapper.fromProductList(service.findAll());
    }

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return mapper.fromProduct(service.findById(id));
    }

    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        Product product = mapper.toProduct(productDto);
        return mapper.fromProduct(service.add(product));
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@RequestBody ProductDto newProductDto, @PathVariable Long id) {
        Product newProduct = mapper.toProduct(newProductDto);
        return mapper.fromProduct(service.replaceAt(id, newProduct));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }
}
