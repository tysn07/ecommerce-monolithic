package com.sparta.ecommerceproject.kakaopay.controller;

import com.sparta.ecommerceproject.kakaopay.dto.response.CancelResDto;
import com.sparta.ecommerceproject.kakaopay.dto.response.PayApproveResDto;
import com.sparta.ecommerceproject.kakaopay.service.KakaoPayService;
import com.sparta.ecommerceproject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final OrderService orderService;
    @GetMapping("/ready/{orderId}")
    public ResponseEntity<?> getRedirectUrl(@PathVariable Long orderId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body(kakaoPayService.getRedirectUrl(orderId));
    }

    @GetMapping("/success/{orderId}")
    public ResponseEntity<?> afterGetRedirectUrl(@PathVariable Long orderId,@RequestParam("pg_token") String pgToken) throws Exception {
        PayApproveResDto kakaoApprove = kakaoPayService.getApprove(pgToken,orderId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(kakaoApprove);
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancel(@PathVariable Long orderId) throws Exception {
        CancelResDto cancelResDto = kakaoPayService.kakaoCancel(orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cancelResDto);
    }

}

