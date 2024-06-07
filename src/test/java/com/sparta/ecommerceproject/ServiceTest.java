package com.sparta.ecommerceproject;

import com.sparta.ecommerceproject.global.security.UserDetailsImpl;
import com.sparta.ecommerceproject.order.repository.OrderRepository;
import com.sparta.ecommerceproject.order.service.OrderService;
import com.sparta.ecommerceproject.product.dto.ProductRequestDto;
import com.sparta.ecommerceproject.product.repository.ProductRepository;
import com.sparta.ecommerceproject.product.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @MockBean
    UserDetailsImpl userDetails;


    @Test
    void order() throws Exception {
        ProductRequestDto productRequestDto = new ProductRequestDto("fr",476L,"efg",778L);
        productService.createProduct(productRequestDto);
        Assertions.assertThat(3).isEqualTo(3);

    }
}
