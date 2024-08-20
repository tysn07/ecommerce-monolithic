package com.ecommerceproject.kakaopay.service;

import com.ecommerceproject.kakaopay.dto.response.CancelResDto;
import com.ecommerceproject.kakaopay.dto.response.PayApproveResDto;
import com.ecommerceproject.kakaopay.dto.response.PayReadyResDto;
import com.ecommerceproject.order.entity.OrderState;
import com.ecommerceproject.kakaopay.dto.request.CancelRequestDto;
import com.ecommerceproject.kakaopay.dto.request.PayInfoDto;
import com.ecommerceproject.kakaopay.dto.request.PayRequestDto;
import com.ecommerceproject.order.repository.OrderRepository;
import com.ecommerceproject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class KakaoPayService {

    private final MakeRequest makeRequest;
    private final OrderRepository orderRepository;
    private final OrderService orderService;


    private String adminKey ="1ce39d67c071c7d7a22bd4a2aef360a1";

    @Transactional
    public PayReadyResDto getRedirectUrl(Long orderId)throws Exception{
        HttpHeaders headers=new HttpHeaders();
        String auth = "KakaoAK " + adminKey;
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization",auth);
        PayRequestDto payRequestDto = makeRequest.getReadyRequest(createPayInfo(orderId),orderId);
        HttpEntity<MultiValueMap<String, String>> urlRequest = new HttpEntity<>(payRequestDto.getMap(), headers);
        RestTemplate rt = new RestTemplate();
        PayReadyResDto payReadyResDto = rt.postForObject(payRequestDto.getUrl(), urlRequest, PayReadyResDto.class);
        orderRepository.getReferenceById(orderId).updateTid(payReadyResDto.getTid());
        return payReadyResDto;
    }

    @Transactional
    public PayApproveResDto getApprove(String pgToken, Long orderId)throws Exception{
        String tid= orderRepository.getReferenceById(orderId).getKakaoTid();
        HttpHeaders headers=new HttpHeaders();
        String auth = "KakaoAK " + adminKey;
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization",auth);
        PayRequestDto payRequestDto = makeRequest.getApproveRequest(tid,pgToken);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(payRequestDto.getMap(), headers);
        RestTemplate rt = new RestTemplate();
        PayApproveResDto payApproveResDto = rt.postForObject(payRequestDto.getUrl(), requestEntity, PayApproveResDto.class);
        orderRepository.getReferenceById(orderId).changeState(OrderState.PREPARING);
        return payApproveResDto;
    }
    public CancelResDto kakaoCancel(Long orderId){
        String tid= orderRepository.getReferenceById(orderId).getKakaoTid();
        HttpHeaders headers=new HttpHeaders();
        String auth = "KakaoAK " + adminKey;
        headers.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization",auth);
        CancelRequestDto cancelRequestDto = makeRequest.getCancelRequest(tid,orderService.getTotalPrice(orderId));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(cancelRequestDto.getMap(), headers);
        RestTemplate rt = new RestTemplate();
        CancelResDto cancelResDto = rt.postForObject(cancelRequestDto.getUrl(),requestEntity,CancelResDto.class);
        return cancelResDto;
    }

    public PayInfoDto createPayInfo(Long orderId){
        PayInfoDto payInfoDto = new PayInfoDto();
        payInfoDto.setPrice(orderService.getTotalPrice(orderId));
        payInfoDto.setItemName("TenCompany");
        return payInfoDto;
    }




}

