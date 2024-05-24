package com.three.alcoholshoppingmall.project.login;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.login.token.TokenManager;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
@Tag(name = "LoginController", description = "로그인/회원가입")
public class LoginController {

    private final LoginService loginService;
    private final LoginRepository loginRepository;
    private final TokenManager tokenManager;
    private final BCryptPasswordEncoder encoder;

    @PostMapping
    @Operation(summary = "로그인", description = "유저 토큰 발급")
    public ResponseEntity<String> loginUser(@RequestBody Login login) {
        User dbuser = loginRepository.findByEmail(login.getEmail());

        if (dbuser == null || !encoder.matches(login.getPassword(), dbuser.getPassword())) {
            throw new BizException(ErrorCode.CHECKEMAILPASSWORD);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tokenManager.generateToken(dbuser));
    }

    @PostMapping("create")
    @Operation(summary = "회원가입", description = "회원 가입 처리")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto) {
        loginService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }

    // 이메일 찾기
//    @GetMapping("/findEmail")
//    @Operation(summary = "이메일 찾기", description = "전화번호와 생년월일을 입력하면 이메일을 찾아줍니다.")
//    public ResponseEntity<String> findEmail(@RequestParam String phone, @RequestParam String birthdate) {
//        String email = loginService.findEmail(phone, birthdate);
//        if (email != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(email);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이메일을 찾을 수 없습니다.");
//    }
//        }
//
//    // 비밀번호 찾기
//    @GetMapping("/findPassword")
//    @Operation(summary = "비밀번호 찾기", description = "이메일, 전화번호를 입력하면 비밀번호를 찾아줍니다.")
//    public String findPassword(@RequestParam String email, @RequestParam String phone) {
//        return loginService.findPassword(email, phone);
//    }
}
