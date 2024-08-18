package com.sparta.ecommerceproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {

    private String email;

    private String username;

    private String password;

    private String adminToken = "";
}
