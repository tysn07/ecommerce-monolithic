package com.sparta.ecommerceproject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequestDto {

    private String name;

    private Long price;

    private String description;

    private Long stock;
}
