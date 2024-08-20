package com.ecommerceproject.global.jwt;


import com.ecommerceproject.user.entity.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.ecommerceproject.global.security.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "jwt 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String tokenValue = jwtUtil.getTokenFromCookie(req);

        tokenValue = jwtUtil.resolveToken(tokenValue);

        if (StringUtils.hasText(tokenValue)) {
            try {

                Claims payload = jwtUtil.getUserInfoFromToken(tokenValue);

                Long userId = Long.valueOf(payload.getSubject());
                String username = payload.get("username", String.class);
                String email = payload.get("email", String.class);
                String role = payload.get("role", String.class);

                UserRoleEnum roleEnum;

                if (role.equals("USER")) {
                    roleEnum = UserRoleEnum.USER;
                } else {
                    roleEnum = UserRoleEnum.ADMIN;
                }

                setAuthentication(userId, username, email, roleEnum);

            } catch (SecurityException | MalformedJwtException | SignatureException e) {
                log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
            } catch (ExpiredJwtException e) {
                log.error("Expired JWT token, 만료된 JWT token 입니다.");
            } catch (UnsupportedJwtException e) {
                log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
            } catch (IllegalArgumentException e) {
                log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
            }
        }
        filterChain.doFilter(req, res);
    }

    // 인증 처리
    public void setAuthentication(Long userId, String username, String email, UserRoleEnum role) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(userId, username, email, role);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(Long userId, String username, String email,
                                                UserRoleEnum role) {
        UserDetails userDetails = userDetailsService.getUser(userId, username, email, role);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
    }
}

