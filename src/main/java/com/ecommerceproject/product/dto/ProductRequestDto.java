package com.ecommerceproject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDto {

    private String name;

    private Long price;

    private String description;

    private Long stock;
}
