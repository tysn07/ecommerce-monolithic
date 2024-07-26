package com.sparta.ecommerceproject.order.dto;

import com.sparta.ecommerceproject.address.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderResponseDto {

    private Long orderId;
    private String address;


    public OrderResponseDto(Long orderId, Address address) {
        this.orderId = orderId;
        this.address = address.getAddress();
    }


}
