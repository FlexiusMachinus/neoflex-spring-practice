package com.fmachinus.practice.dto.mapping;

import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto fromProduct(Product product);

    Product toProduct(ProductDto productDto);

    List<ProductDto> fromProductList(List<Product> productList);

    List<Product> toProductList(List<ProductDto> productDtoList);
}
