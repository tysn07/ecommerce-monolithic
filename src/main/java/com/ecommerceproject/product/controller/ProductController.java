package com.ecommerceproject.product.controller;

import com.ecommerceproject.product.dto.ProductRequestDto;
import com.ecommerceproject.product.dto.ProductResponse;
import com.ecommerceproject.product.dto.ProductUpdateRequest;
import com.ecommerceproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
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

    @PostMapping("{productId}/image")
    public void uploadProductImage(@PathVariable Long productId, @RequestParam("file") MultipartFile file) throws IOException {
        productService.uploadProductImage(productId,file);
    }

    @GetMapping("{productId}/image")
    public String getProductImage(@PathVariable Long productId) throws IOException {
        return productService.getProductImage(productId);
    }

}
