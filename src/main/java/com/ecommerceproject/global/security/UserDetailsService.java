package com.ecommerceproject.global.security;

import com.ecommerceproject.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ecommerceproject.user.entity.User;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    public UserDetails getUser(Long userId, String username, String email, UserRoleEnum role)
            throws UsernameNotFoundException {
        User user = new User(userId, username, email, role);
        return new UserDetailsImpl(user);
    }

}
