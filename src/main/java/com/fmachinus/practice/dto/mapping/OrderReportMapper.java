package com.fmachinus.practice.dto.mapping;

import com.fmachinus.practice.dto.OrderReportDto;
import com.fmachinus.practice.utils.OrderReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OrderMapper.class, ProductMapper.class })
public abstract class OrderReportMapper {

    @Mapping(source = "ordersReadonly", target = "orders")
    @Mapping(source = "productSalesReadonly", target = "productSales")
    public abstract OrderReportDto fromOrderReport(OrderReport orderReport);
}
