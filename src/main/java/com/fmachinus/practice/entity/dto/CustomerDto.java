package com.fmachinus.practice.entity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal cash;
}
