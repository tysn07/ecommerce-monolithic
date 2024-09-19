package com.ecommerceproject.product.service;

import com.ecommerceproject.product.dto.ProductRequestDto;
import com.ecommerceproject.product.dto.ProductResponse;
import com.ecommerceproject.product.dto.ProductUpdateRequest;
import com.ecommerceproject.product.entity.Product;
import com.ecommerceproject.product.repository.ProductRepository;
import com.ecommerceproject.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import java.io.IOException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final S3Service s3Service;

    String bucketName = "sonshop-product";

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

    public void uploadProductImage(Long productId, MultipartFile file) throws IOException {
        String imageKey = UUID.randomUUID().toString();
        String format = "product-images/%s/%s".formatted(productId,
                imageKey) + ".PNG";
        s3Service.putObject(
                bucketName, format,
                file);
        String url = "https://" + bucketName + ".s3" + ".ap-northeast-2.amazonaws.com/" + format;
        Product product = getProduct(productId);
        product.updateImageUrl(url);
        productRepository.save(product);
    }

    public String getProductImage(Long productId) {
        try {
            return getProduct(productId).getImageUrl();
        } catch (NoSuchKeyException e) {
            throw new RuntimeException("요청한 상품 이미지가 S3 버킷에 존재하지 않습니다. 이미지 키를 확인해주세요.");
        }
    }

}
