package com.sparta.ecommerceproject.product.controller;

import com.sparta.ecommerceproject.global.security.UserDetailsImpl;
import com.sparta.ecommerceproject.product.dto.ProductRequestDto;
import com.sparta.ecommerceproject.product.dto.ProductResponse;
import com.sparta.ecommerceproject.product.dto.ProductUpdateRequest;
import com.sparta.ecommerceproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    private final ProductService productService;

   // @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<String> createAdminProduct(@RequestBody ProductRequestDto requestDto) {
        productService.createProduct(requestDto);
        return ResponseEntity.status(201)
                .body("Product created successfully");
    }
   // @Secured("ROLE_ADMIN")
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest productRequest) {
        productService.updateProduct(productId, productRequest);
        return ResponseEntity.status(200)
                .body("Product update successfully");
    }
    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(200)
                .body("Product delete successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());

    }
}
