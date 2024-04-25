package com.three.alcoholshoppingmall.project.conf;

import com.three.alcoholshoppingmall.project.login.token.TokenManager;
import com.three.alcoholshoppingmall.project.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        System.out.println(request.getRequestURI());
        if (request.getRequestURI().contains("login") ||
                request.getRequestURI().contains("map") ||
                request.getRequestURI().contains("main") ||
                request.getRequestURI().contains("event") ||
                request.getRequestURI().contains("search")) {
            return true;
        }

        if (token == null || !token.contains("Bearer ")) {
            System.out.println("토큰 없음");
            return false;
        }

        System.out.println(token);
        try {
            System.out.println(token.substring("Bearer ".length()));
            Jws<Claims> jws = tokenManager.validateToken(token.substring("Bearer ".length()).trim());

            List<SimpleGrantedAuthority> roles = Stream.of(jws.getPayload().get("email").toString())
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            System.out.println(roles);

            Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(
                    User.builder()
                            .email(jws.getPayload().get("email").toString())
                            .id(jws.getPayload().get("id", Long.class))
                            .build(),
                    null,
                    roles
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            System.out.println("토큰 만료");
            throw new RuntimeException("JWT 토큰 만료");
        } catch (Exception e) {
            System.out.println("토큰 검증 실패");
            throw new RuntimeException("JWT 토큰 검증 실패");
        }

        return true;
    }
}
