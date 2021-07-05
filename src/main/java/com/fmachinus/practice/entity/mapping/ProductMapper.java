package com.fmachinus.practice.entity.mapping;

import com.fmachinus.practice.entity.Product;
import com.fmachinus.practice.entity.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto fromProduct(Product product);
    Product toProduct(ProductDto productDto);
}
