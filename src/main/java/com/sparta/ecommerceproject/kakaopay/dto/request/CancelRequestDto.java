package com.sparta.ecommerceproject.kakaopay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
@Getter
@AllArgsConstructor
public class CancelRequestDto {
    private String url;
    private LinkedMultiValueMap<String,String> map;
}
