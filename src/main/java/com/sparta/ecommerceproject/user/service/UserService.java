package com.sparta.ecommerceproject.user.service;

import com.sparta.ecommerceproject.user.dto.LoginRequestDto;
import com.sparta.ecommerceproject.user.dto.SignupRequestDto;
import com.sparta.ecommerceproject.user.entity.User;
import com.sparta.ecommerceproject.user.entity.UserRoleEnum;
import com.sparta.ecommerceproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZaHgTBcXukeZygoC";
    public void signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String email = signupRequestDto.getEmail();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new RuntimeException("중복된 username입니다.");
        }

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new RuntimeException("중복된 email입니다.");
        }

        UserRoleEnum role =UserRoleEnum.USER;
        if(signupRequestDto.isAdmin()){
            if(!ADMIN_TOKEN.equals(signupRequestDto.getAdminToken())){
                throw new RuntimeException("관리자 암호가 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        userRepository.save(User.builder()
                .email(email)
                .username(username)
                .password(password)
                .role(role)
                .build());

    }

    public User login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 계정입니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

}
