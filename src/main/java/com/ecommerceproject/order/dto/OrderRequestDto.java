package com.ecommerceproject.order.dto;
import lombok.Getter;

import java.util.Map;
@Getter
public class OrderRequestDto {

    private Map<Long,Long> basket;
    private Long addressId;

}
