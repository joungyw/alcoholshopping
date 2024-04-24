package com.three.alcoholshoppingmall.project.login;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.login.token.TokenManager;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;
    private final LoginRepository loginRepository;
    private final TokenManager tokenManager;

    @PostMapping
    public String loginUser(@RequestBody UserDto userDto) {
        User dbuser = loginRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());

        if (dbuser == null) {
            throw new BizException(ErrorCode.CHECKIDPASSWORD);
        }
        return tokenManager.generateToken(dbuser);
    }

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto) {
        loginService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료");
    }
}
