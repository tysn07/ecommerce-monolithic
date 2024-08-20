package com.ecommerceproject.order.dto;

import com.ecommerceproject.order.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
