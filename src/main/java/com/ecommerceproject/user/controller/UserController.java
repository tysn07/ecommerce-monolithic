package com.ecommerceproject.user.controller;

import com.ecommerceproject.user.service.UserService;
import com.ecommerceproject.global.jwt.JwtUtil;
import com.ecommerceproject.global.security.UserDetailsImpl;
import com.ecommerceproject.user.dto.LoginRequestDto;
import com.ecommerceproject.user.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.ecommerceproject.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto,
                                        HttpServletResponse response) {
        User loginedUser = userService.login(loginRequestDto);
        String token = jwtUtil.createToken(loginedUser.getId(), loginedUser.getEmail(),
                loginedUser.getUsername(), loginedUser.getRole());
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        jwtUtil.addJwtToCookie(token, response);

        return ResponseEntity.ok().body(loginedUser.getRole().toString());
    }

    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> checkAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok().body(userService.checkAdmin(userDetails));
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUsername(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok().body(userService.getUsername(userDetails));
    }



}

