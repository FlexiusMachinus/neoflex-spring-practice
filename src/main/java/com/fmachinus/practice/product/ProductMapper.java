package com.fmachinus.practice.product;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto fromProduct(Product product);
    Product toProduct(ProductDto productDto);
}
