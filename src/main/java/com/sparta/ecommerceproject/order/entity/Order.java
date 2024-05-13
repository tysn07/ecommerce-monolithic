package com.sparta.ecommerceproject.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long addressId;

    public Order(Long userId,Long addressId){
        this.userId = userId;
        this.addressId = addressId;
    }

}
