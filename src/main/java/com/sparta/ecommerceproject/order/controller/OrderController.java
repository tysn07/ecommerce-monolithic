package com.sparta.ecommerceproject.order.controller;

import com.sparta.ecommerceproject.global.security.UserDetailsImpl;
import com.sparta.ecommerceproject.order.dto.OrderDetailResponseDto;
import com.sparta.ecommerceproject.order.dto.OrderRequestDto;
import com.sparta.ecommerceproject.order.dto.OrderResponseDto;
import com.sparta.ecommerceproject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<String> makeOrder(@RequestBody OrderRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        orderService.createOrder(requestDto.getBasket(),userDetails, requestDto.getAddressId());
        return ResponseEntity.status(201)
                .body("order created");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetailResponseDto>> getOrder(@PathVariable Long orderId){
            return ResponseEntity.status(200).body(orderService.getOrderDetailList(orderId));
    }

    @GetMapping("/userorder")
    public ResponseEntity<List<OrderResponseDto>> getUserOrder(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(200).body(orderService.getOrderList(userDetails));}

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
            orderService.deleteOrder(orderId);
        return ResponseEntity.status(201).body("cancel order");
    }




}
