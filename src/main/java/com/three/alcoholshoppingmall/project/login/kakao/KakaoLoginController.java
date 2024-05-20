package com.three.alcoholshoppingmall.project.login.kakao;


import com.three.alcoholshoppingmall.project.login.token.Token;
import com.three.alcoholshoppingmall.project.user.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("kakao")
@Tag(name = "KakaoLoginController",description = "카카오 로그인")
public class KakaoLoginController {

    private final KakaoLoginService loginService;

    @GetMapping("login")
    public ResponseEntity<Token> kakaoLogin(@RequestHeader String token){

        Token tokens = loginService.userAuthToken(token);
        System.out.println(tokens);

        return ResponseEntity.status(HttpStatus.OK).body(tokens);
    }
}
