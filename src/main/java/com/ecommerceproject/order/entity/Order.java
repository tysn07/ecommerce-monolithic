package com.ecommerceproject.order.entity;

import com.ecommerceproject.global.Timestamped;
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
    private String address;

    @Column
    private String KakaoTid;

    @Enumerated(value = EnumType.STRING)
    private OrderState state;
    public Order(Long userId,String address,OrderState state){
        this.userId = userId;
        this.address = address;
        this.state = state;
    }
    public void updateTid(String tid){
        this.KakaoTid=tid;
    }
    public void changeState(OrderState state){this.state = state;}


}
