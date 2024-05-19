package com.three.alcoholshoppingmall.project.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "UserController", description = "회원 정보 수정, 탈퇴")
public class UserController {

    private final UserService userService;

    @PutMapping(value = "update/{email}")// 회원정보 수정
    public User updateUser(@PathVariable String email,
                           @RequestBody UserUpdate userUpdate) {

        return userService.updateUser(email, userUpdate);
    }
    @PutMapping("withdraw/{email}") // 회원정보 탈퇴
    public User withdrawUser(@PathVariable String email) {
        return userService.withdrawUser(email);
    }
}
