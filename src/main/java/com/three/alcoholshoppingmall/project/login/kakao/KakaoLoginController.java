package com.three.alcoholshoppingmall.project.login.kakao;


import com.three.alcoholshoppingmall.project.login.token.Token;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("kakao")
@Tag(name = "KakaoLoginController",description = "카카오 로그인")
public class KakaoLoginController {

    private final KakaoLoginService loginService;

    // 인가코드 받는 함수
    @GetMapping("login")
    public ResponseEntity<Token> authToken(@RequestHeader String token){

        Token tokens = loginService.userAuthToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(tokens);
    }

}
