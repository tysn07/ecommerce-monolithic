package com.ecommerceproject.order.service;

import com.ecommerceproject.global.security.UserDetailsImpl;
import com.ecommerceproject.order.dto.OrderDetailResponseDto;
import com.ecommerceproject.order.dto.OrderResponseDto;
import com.ecommerceproject.order.entity.Order;
import com.ecommerceproject.order.entity.OrderDetail;
import com.ecommerceproject.order.entity.OrderState;
import com.ecommerceproject.address.entity.Address;
import com.ecommerceproject.address.service.AddressService;
import com.ecommerceproject.order.repository.OrderDetailRepository;
import com.ecommerceproject.order.repository.OrderRepository;
import com.ecommerceproject.product.repository.ProductRepository;
import com.ecommerceproject.product.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final AddressService addressService;
    private final ProductRepository productRepository;
    @Transactional
    public void createOrder(Map<Long,Long> basket, UserDetailsImpl userDetails, String address) throws Exception {
        if(address.isEmpty()){
            throw new RuntimeException("주소를 선택하십시오");
        }
        checkBasket(basket);
        Order order = new Order(userDetails.getUser().getId(),address, OrderState.NOTPAYED);
        orderRepository.save(order);
        for(Long key:basket.keySet()){
            updateStock(key,basket.get(key));
            OrderDetail orderDetail= new OrderDetail(order.getId(),key,basket.get(key),productService.getProduct(key).getPrice(),productService.getProduct(key).getName());
            orderDetailRepository.save(orderDetail);
        }

    }
    public List<OrderDetailResponseDto> getOrderDetailList(Long orderId){
        List<OrderDetail> listOfOrderedProducts = orderDetailRepository.findOrderDetailsByOrderId(orderId);
        return listOfOrderedProducts.stream().map(OrderDetailResponseDto::new).toList();
    }
    @Transactional
    public void deleteOrder(Long orderId){
        orderDetailRepository.deleteAll(orderDetailRepository.findOrderDetailsByOrderId(orderId));
        orderRepository.delete(orderRepository.getById(orderId));
    }

    public boolean checkUser(UserDetailsImpl userDetails,Long orderId){
        return Objects.equals(userDetails.getUser().getId(), orderRepository.getById(orderId).getUserId());
    }

    public void updateStock(Long productId,Long quantity){
        productService.getProduct(productId).updateStockAfterOrder(quantity);

    }

    public boolean checkStock(Long productId,Long quantity){
        return productService.getProduct(productId).getStock() - quantity >= 0;
    }

    public List<OrderResponseDto> getOrderList(UserDetailsImpl userDetails){
        List<Order> orderList = orderRepository.findOrdersByUserId(userDetails.getUser().getId());
        List<OrderResponseDto> ResponseList= new ArrayList<OrderResponseDto>();
        for(Order order:orderList){
            OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(),order.getAddress(),order.getState().toString());
            ResponseList.add(orderResponseDto);
        }
        return ResponseList;
    }

    public void checkBasket(Map<Long,Long> basket) throws Exception {
        for(Long key:basket.keySet()){
            if(!checkStock(key,basket.get(key))){throw new Exception("id:"+key+" 수량부족");}
        }
    }

    public Order getOrder(Long orderId){
        return orderRepository.getById(orderId);
    }

    public Long getTotalPrice(Long orderId){
        List<OrderDetail> ListofOrderDetail = orderDetailRepository.findOrderDetailsByOrderId(orderId);
        Long totalPrice=0L;
        for(OrderDetail orderDetail:ListofOrderDetail){
            totalPrice+=orderDetail.getPrice()*orderDetail.getQuantity();
        }
        return totalPrice;
    }




}
