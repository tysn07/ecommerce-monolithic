package com.sparta.ecommerceproject.product.entity;

import com.sparta.ecommerceproject.product.dto.ProductUpdateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private Long price;
    @Column
    private String description;
    @Column
    private Long stock;



    @Builder
    public Product(String name, Long price, String description, Long stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;

    }

    public void update(ProductUpdateRequest productRequest) {
        this.name = productRequest.getName();
        this.price = productRequest.getPrice();
        this.description = productRequest.getDescription();
    }

    public void updateStockAfterOrder(Long quantity) {this.stock = stock - quantity;}




}
