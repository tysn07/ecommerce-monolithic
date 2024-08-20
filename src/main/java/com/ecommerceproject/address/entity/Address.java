package com.ecommerceproject.address.entity;

import com.ecommerceproject.address.dto.AddressRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column
    private Long userId;

    public Address(AddressRequestDto requestDto, Long userId){
        this.address = requestDto.getAddress();
        this.userId = userId;
    }
}
