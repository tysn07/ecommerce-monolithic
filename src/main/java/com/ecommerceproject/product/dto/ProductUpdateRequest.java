package com.ecommerceproject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductUpdateRequest {

    private String name;

    private Long price;

    private String description;

}
