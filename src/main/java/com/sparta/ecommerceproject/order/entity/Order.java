package com.sparta.ecommerceproject.order.entity;

import com.sparta.ecommerceproject.global.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long addressId;

    @Column
    private String KakaoTid;

    @Enumerated(value = EnumType.STRING)
    private OrderState state;
    public Order(Long userId,Long addressId,OrderState state){
        this.userId = userId;
        this.addressId = addressId;
        this.state = state;
    }
    public void updateTid(String tid){
        this.KakaoTid=tid;
    }
    public void changeState(OrderState state){this.state = state;}


}
