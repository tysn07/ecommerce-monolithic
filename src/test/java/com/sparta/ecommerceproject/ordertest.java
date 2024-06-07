package com.sparta.ecommerceproject;

import com.sparta.ecommerceproject.global.security.UserDetailsImpl;
import com.sparta.ecommerceproject.order.entity.Order;
import com.sparta.ecommerceproject.order.entity.OrderDetail;
import com.sparta.ecommerceproject.order.entity.OrderState;
import com.sparta.ecommerceproject.order.repository.OrderDetailRepository;
import com.sparta.ecommerceproject.order.repository.OrderRepository;
import com.sparta.ecommerceproject.order.service.OrderService;
import com.sparta.ecommerceproject.product.entity.Product;
import com.sparta.ecommerceproject.product.repository.ProductRepository;
import com.sparta.ecommerceproject.user.entity.User;
import com.sparta.ecommerceproject.user.entity.UserRoleEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;

@DataJpaTest
public class ordertest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;



/*
    @Test
    @DisplayName("ordertest")
    void ordertest() throws Exception {
        Product product = new Product("sdksd",57L,"sde",1000L);
        productRepository.save(product);
        User user  = new User(1L,"name","email", UserRoleEnum.USER);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Map<Long,Long> basket = new HashMap<>();
        basket.put(1L,1L);
        createOrder(basket,userDetails,1L);
        createOrder(basket,userDetails,1L);

        List<Order> result = orderRepository.findAll();
        Assertions.assertThat(productRepository.findById(1L).get().getStock()).isEqualTo(998L);

    }
*/
   /* @Test
    @DisplayName("ordertest")
    void Concurrency() throws InterruptedException {
        Product product = new Product("sdksd",57L,"sde",1000L);
        productRepository.save(product);
        User user  = new User(1L,"name","email", UserRoleEnum.USER);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Map<Long,Long> basket = new HashMap<>();
        basket.put(1L,1L);
        ExecutorService service = Executors.newFixedThreadPool(2);

        CountDownLatch latch = new CountDownLatch(2);
        for(int i=0;i<2;i++){
            service.execute(() -> {


                try {
                    createOrder(basket,userDetails,1L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                latch.countDown();


            });


        }
               latch.await();
        Assertions.assertThat(productRepository.findById(1L).get().getStock()).isEqualTo(998L);


    }*/



    @Test
    @DisplayName("ordertest")
    void Concurrency() throws Exception {
        Product product = new Product("sdksd",57L,"sde",1000L);
        productRepository.save(product);
        User user  = new User(1L,"name","email", UserRoleEnum.USER);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Map<Long,Long> basket = new HashMap<>();
        basket.put(1L,1L);
        ExecutorService service = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

               service.submit(() -> {
                   try {
                       createOrder(basket, userDetails, 1L);
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   } finally {
                       latch.countDown();
                   }
               });



        latch.await();
        Assertions.assertThat(productRepository.findById(1L).get().getStock()).isEqualTo(998L);


    }

    public void createOrder(Map<Long,Long> basket, UserDetailsImpl userDetails, Long addressId) throws Exception {
        Order order = new Order(userDetails.getUser().getId(),addressId, OrderState.NOTPAYED);
        orderRepository.save(order);
        for(Long key:basket.keySet()){
            OrderDetail orderDetail= new OrderDetail(order.getId(),key,basket.get(key),productRepository.getReferenceById(key).getPrice(),productRepository.getReferenceById(key).getName());
            orderDetailRepository.save(orderDetail);
            updateStock(key,basket.get(key));
        }

    }

    public void updateStock(Long productId,Long quantity){
        Product product =  productRepository.getReferenceById(productId);
        product.updateStockAfterOrder(quantity);

    }

}
