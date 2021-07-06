package com.fmachinus.practice.controller;

import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.entity.dto.ProductDto;
import com.fmachinus.practice.entity.mapping.ProductMapper;
import com.fmachinus.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<ProductDto> findAllProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = service.findAll();
        productList.forEach(product -> productDtoList.add(ProductMapper.MAPPER.fromProduct(product)));
        return productDtoList;
    }

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return ProductMapper.MAPPER.fromProduct(service.findById(id));
    }

    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toProduct(productDto);
        return ProductMapper.MAPPER.fromProduct(service.add(product));
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@RequestBody ProductDto newProductDto, @PathVariable Long id) {
        Product newProduct = ProductMapper.MAPPER.toProduct(newProductDto);
        return ProductMapper.MAPPER.fromProduct(service.replaceAt(id, newProduct));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }
}
