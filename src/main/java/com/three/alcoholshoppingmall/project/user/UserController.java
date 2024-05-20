package com.three.alcoholshoppingmall.project.user;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User",description = "유저 관련 컨트롤러입니다.")
public class UserController {

    @GetMapping("/info")
    @Operation(summary = "유저 정보",
            description = "토큰으로 유저 정보 주는 함수입니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserInfo> userInfo(@AuthenticationPrincipal User user){

        UserInfo info = UserInfo.builder()
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress() + " " + user.getAddress2())
                .build();

        System.out.println(info);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }
}
