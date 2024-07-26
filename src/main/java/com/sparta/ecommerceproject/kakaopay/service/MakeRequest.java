package com.sparta.ecommerceproject.kakaopay.service;

import com.sparta.ecommerceproject.kakaopay.dto.request.CancelRequestDto;
import com.sparta.ecommerceproject.kakaopay.dto.request.PayInfoDto;
import com.sparta.ecommerceproject.kakaopay.dto.request.PayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

@Component
@RequiredArgsConstructor
public class MakeRequest {

    public PayRequestDto getReadyRequest(PayInfoDto payInfoDto,Long orderId){
        LinkedMultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("cid","TC0ONETIME");
        map.add("partner_order_id","partner_order_id");
        map.add("partner_user_id","ten");
        map.add("item_name",payInfoDto.getItemName());
        map.add("quantity","1");
        map.add("total_amount",payInfoDto.getPrice()+"");
        map.add("tax_free_amount", "0");
        map.add("approval_url", "http://localhost:8080/payment/success/"+orderId); // 성공 시 redirect url
        map.add("cancel_url", "http://localhost:8080/payment/cancel"); // 취소 시 redirect url
        map.add("fail_url", "http://localhost:8080/payment/fail"); // 실패 시 redirect url
        return new PayRequestDto("https://kapi.kakao.com/v1/payment/ready",map);
    }

    public PayRequestDto getApproveRequest(String tid, String pgToken){
        LinkedMultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("cid", "TC0ONETIME");
        map.add("tid", tid);
        map.add("partner_order_id", "partner_order_id");
        map.add("partner_user_id", "ten");
        map.add("pg_token", pgToken);
        return new PayRequestDto("https://kapi.kakao.com/v1/payment/approve",map);
    }

    public CancelRequestDto getCancelRequest(String tid, Long price){
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("cid", "TC0ONETIME");
        map.add("tid", tid);
        map.add("cancel_amount", price+"");
        map.add("cancel_tax_free_amount", "0");
        map.add("cancel_vat_amount", "0");
        return new CancelRequestDto("https://kapi.kakao.com/v1/payment/cancel",map);
    }
}
