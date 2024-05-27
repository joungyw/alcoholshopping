package com.three.alcoholshoppingmall.project.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "유저 관련 컨트롤러입니다.")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    @Operation(summary = "회원 정보",
            description = "로그인한 회원정보를 조회하는 기능입니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<User>> userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<User> list = userService.userInfo(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @PutMapping("updateUser")// 회원정보 수정
    @Operation(summary = "회원정보 수정", description = "회원정보를 수정하는 코드입니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserUpdate userUpdate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String email = user.getEmail();
        String returnvalue = userService.updateUser(email, userUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);
    }

//    @PutMapping("withdraw/{email}") // 회원정보 탈퇴
//    @SecurityRequirement(name = "bearerAuth")
//    public ResponseEntity<String> withdrawUser(@PathVariable String email) {
//        userService.withdrawUser(email);
//        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴가 완료되었습니다.");
//    }
//    @PostMapping("updatePw")// 비밀번호 수정
//    @SecurityRequirement(name = "bearerAuth")
//    public ResponseEntity<String> updatePw(@Valid @RequestBody PwUpdate pwUpdate){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        String email = user.getEmail();
//        String returnvalue = userService.updatePw(email, pwUpdate);
//        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);
//    }

}