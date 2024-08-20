package com.ecommerceproject.kakaopay.dto.response;

import lombok.Getter;

@Getter
public class Amount {
    private int total;
    private int tax_free;
    private int tax;
}
