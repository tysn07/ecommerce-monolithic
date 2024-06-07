package com.sparta.ecommerceproject;

import com.sparta.ecommerceproject.global.security.UserDetailsImpl;
import com.sparta.ecommerceproject.order.repository.OrderRepository;
import com.sparta.ecommerceproject.order.service.OrderService;
import com.sparta.ecommerceproject.product.entity.Product;
import com.sparta.ecommerceproject.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@DataJpaTest
public class Repotest {

    @Autowired
    ProductRepository productRepository;


    @Test
    @DisplayName("repoTest")
    void CreateProduct(){
        Product product = new Product("sdksd",57L,"sde",98L);
        Product product2 = new Product("sdksdfe",5733L,"sefde",984L);
        Product product3 = new Product("sdksd",57L,"sde",98L);
        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);
        List<Product> result = productRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(3);

    }




}
