package com.sparta.ecommerceproject.product.service;

import com.sparta.ecommerceproject.product.dto.ProductRequestDto;
import com.sparta.ecommerceproject.product.dto.ProductResponse;
import com.sparta.ecommerceproject.product.dto.ProductUpdateRequest;
import com.sparta.ecommerceproject.product.entity.Product;
import com.sparta.ecommerceproject.product.repository.ProductRepository;
import com.sparta.ecommerceproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDto productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .stock(productRequest.getStock())
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest productRequest) {
        Product product = getProduct(productId);
        product.update(productRequest);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("해당 상품이 존재하지 않습니다.")
        );
    }

    @Transactional
    public void deleteProduct(Long productId){
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductResponse::new).toList();

    }

}
