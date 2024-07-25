package com.sparta.ecommerceproject.user.controller;

import com.sparta.ecommerceproject.global.jwt.JwtUtil;
import com.sparta.ecommerceproject.global.security.UserDetailsImpl;
import com.sparta.ecommerceproject.user.dto.LoginRequestDto;
import com.sparta.ecommerceproject.user.dto.SignupRequestDto;
import com.sparta.ecommerceproject.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.sparta.ecommerceproject.user.entity.User;
import com.sparta.ecommerceproject.user.entity.UserRoleEnum;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
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

    @GetMapping("/loginstatus")
    public ResponseEntity<Boolean> checkStatus(@AuthenticationPrincipal UserDetailsImpl userDetails){
        boolean status = true;
        if(userDetails.getUser().getUsername().isBlank()){
           status = false;
        }
        return ResponseEntity.ok().body(status);
    }
}

