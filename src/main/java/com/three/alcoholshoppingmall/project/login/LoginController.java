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
    @Operation(summary = "로그인", description = "유저 토큰 발급, email: aaa@naver.com,<br> " +
            "password: a123456!")
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

    //     이메일 찾기
    @PostMapping("/findEmail")
    @Operation(summary = "이메일 찾기", description = "전화번호와 생년월일을 입력하면 이메일을 찾아줍니다.")
    public ResponseEntity<String> findEmail(@RequestBody FindEmailDTO findEmailDTO) {
        String email = loginRepository.findByPhoneAndBirthdate(findEmailDTO.getPhone(), findEmailDTO.getBirthdate());
        System.out.println(email);
        if (email != null) {
            return ResponseEntity.status(HttpStatus.OK).body(email);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이메일을 찾을 수 없습니다.");
        }
    }

//    @PostMapping("/findPw")
////    @Operation(summary = "비밀번호 변경", description = "이메일 인증을 하고 임시비번이 발송되면 임시비버")
//    public

    @PostMapping("emailauth")
    @Operation(summary = "이메일 인증", description = "이메일 인증하기")
    public ResponseEntity<String> emailAuth(@RequestBody Email email) {

        System.out.println(email);
        String num = loginService.sendAuthNum(email.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(num);
    }

}
