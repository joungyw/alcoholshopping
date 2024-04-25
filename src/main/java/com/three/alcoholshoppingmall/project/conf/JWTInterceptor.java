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

        if (request.getRequestURI().contains("login") ||
                request.getRequestURI().contains("map") ||
                request.getRequestURI().contains("swagger") ||
                request.getRequestURI().contains("api-docs") ||
                request.getRequestURI().contains("v3/api-docs")) {
            return true;
        }

        if (token == null || !token.contains("Bearer ")) {
            System.out.println("토큰 없음");
            return false;
        }

        try {
            Jws<Claims> jws = tokenManager.validateToken(token.substring("Bearer ".length()).trim());

            List<SimpleGrantedAuthority> roles = Stream.of(jws.getPayload().get("email").toString())
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            User user = User.builder()
                    .email(jws.getPayload().get("email").toString())
                    .nickname(jws.getPayload().get("nickname").toString())
                    .build();

            System.out.println("인터셉터에서 Authentication user 등록");
            System.out.println(user);
            System.out.println("인터셉터에서 Authentication user 등록");

            Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(
                    user,
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
