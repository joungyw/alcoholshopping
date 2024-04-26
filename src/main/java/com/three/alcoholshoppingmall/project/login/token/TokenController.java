package com.three.alcoholshoppingmall.project.login.token;

import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("token")
@Tag(name = "TokenController",description = "토큰 관련 함수")
public class TokenController {

    @PostMapping("userinfo")
    @Operation(summary = "유저 정보",description = "유저 정보 가져오는 함수")
    public ResponseEntity<User> userInfo(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }
}
