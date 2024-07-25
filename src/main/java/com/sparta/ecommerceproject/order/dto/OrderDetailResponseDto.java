package com.sparta.ecommerceproject.order.dto;

import com.sparta.ecommerceproject.order.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailResponseDto {

    private Long productId;
    private Long quantity;
    private Long price;
    private String productName;

    public OrderDetailResponseDto(OrderDetail orderDetail){
        this.productId = orderDetail.getProductId();
        this.quantity = orderDetail.getQuantity();
        this.productName = orderDetail.getProductName();
        this.price = orderDetail.getPrice();
    }


}
