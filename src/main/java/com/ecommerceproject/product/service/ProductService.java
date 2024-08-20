package com.ecommerceproject.product.service;

import com.ecommerceproject.product.dto.ProductRequestDto;
import com.ecommerceproject.product.dto.ProductResponse;
import com.ecommerceproject.product.dto.ProductUpdateRequest;
import com.ecommerceproject.product.entity.Product;
import com.ecommerceproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
