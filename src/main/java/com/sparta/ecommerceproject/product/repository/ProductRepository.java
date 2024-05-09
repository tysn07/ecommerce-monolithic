package com.sparta.ecommerceproject.product.repository;

import com.sparta.ecommerceproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
