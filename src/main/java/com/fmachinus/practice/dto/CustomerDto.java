package com.fmachinus.practice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal cash;
}
