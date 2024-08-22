package com.ecommerceproject.kakaopay.controller;

import com.ecommerceproject.kakaopay.dto.response.CancelResDto;
import com.ecommerceproject.kakaopay.dto.response.PayApproveResDto;
import com.ecommerceproject.kakaopay.service.KakaoPayService;
import com.ecommerceproject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView afterGetRedirectUrl(@PathVariable Long orderId, @RequestParam("pg_token") String pgToken) throws Exception {
        PayApproveResDto kakaoApprove = kakaoPayService.getApprove(pgToken,orderId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/");
        return redirectView;
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancel(@PathVariable Long orderId) throws Exception {
        CancelResDto cancelResDto = kakaoPayService.kakaoCancel(orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cancelResDto);
    }

}

