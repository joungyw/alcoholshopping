package com.three.alcoholshoppingmall.project.login;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.login.token.TokenManager;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
@Tag(name = "LoginController",description = "로그인/회원가입")
public class LoginController {

    private final LoginService loginService;
    private final LoginRepository loginRepository;
    private final TokenManager tokenManager;
    private final BCryptPasswordEncoder encoder;

    @PostMapping
    @Operation(summary = "로그인",description = "유저 토큰 발급")
    public String loginUser(@RequestBody Login login) {
        User dbuser = loginRepository.findByEmail(login.getEmail());

        if (dbuser == null || !encoder.matches(login.getPassword(),dbuser.getPassword())) {
            throw new BizException(ErrorCode.CHECKEMAILPASSWORD);
        }

        return tokenManager.generateToken(dbuser);
    }

    @PostMapping("create")
    @Operation(summary = "회원가입",description = "회원 가입 처리")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto) {
        loginService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }
}
