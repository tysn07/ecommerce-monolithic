package com.sparta.ecommerceproject.order.dto;

import com.sparta.ecommerceproject.address.entity.Address;
import com.sparta.ecommerceproject.order.entity.OrderState;
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
    private String state;

    public OrderResponseDto(Long orderId, Address address,String state) {
        this.orderId = orderId;
        this.address = address.getAddress();
        this.state = state;
    }


}
