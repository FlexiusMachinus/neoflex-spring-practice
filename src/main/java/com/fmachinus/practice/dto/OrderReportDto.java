package com.fmachinus.practice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class OrderReportDto {

    private List<OrderDto> orders;
    private Map<ProductDto, Integer> productSales;
    private BigDecimal revenue;
    private Integer totalSales;
    private ProductDto bestProductBySales;
    private ProductDto bestProductByRevenue;
}
