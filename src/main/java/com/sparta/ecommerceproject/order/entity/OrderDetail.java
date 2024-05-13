package com.sparta.ecommerceproject.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orderdetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orderId;

    @Column
    private Long quantity;

    @Column
    private Long productId;

    @Column
    private String productName;

    @Column
    private Long price;

    public OrderDetail(Long orderId,Long productId,Long quantity,Long price,String productName){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.orderId = orderId;
        this.price = price;
    }


}
